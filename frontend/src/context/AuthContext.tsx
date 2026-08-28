'use client';

import React, { createContext, useContext, useEffect, useState } from 'react';
import { User, AuthResponse, UserProfileResponse } from '@/types';
import { apiRequest, getAuthToken, setAuthToken, clearAuthToken } from '@/lib/api';

interface AuthContextType {
  user: User | null;
  token: string | null;
  isLoading: boolean;
  login: (email: string, password: string) => Promise<void>;
  register: (data: {
    email: string;
    password: string;
    fullName: string;
    role: 'CONSUMER' | 'VENDOR';
    businessName?: string;
  }) => Promise<void>;
  logout: () => void;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export function AuthProvider({ children }: { children: React.ReactNode }) {
  const [user, setUser] = useState<User | null>(null);
  const [token, setTokenState] = useState<string | null>(null);
  const [isLoading, setIsLoading] = useState<boolean>(true);

  useEffect(() => {
    const savedToken = getAuthToken();
    const savedUser = typeof window !== 'undefined' ? localStorage.getItem('fraudshield_user') : null;

    if (savedToken && savedUser) {
      setTokenState(savedToken);
      try {
        setUser(JSON.parse(savedUser));
      } catch (e) {
        setUser(null);
      }
      fetchCurrentUser().finally(() => setIsLoading(false));
    } else {
      setIsLoading(false);
    }
  }, []);

  const fetchCurrentUser = async () => {
    try {
      const profile = await apiRequest<UserProfileResponse>('/users/me');
      const userData: User = {
        id: profile.id,
        email: profile.email,
        fullName: profile.fullName,
        role: profile.role,
        businessName: profile.vendorProfile?.businessName,
      };
      setUser(userData);
      localStorage.setItem('fraudshield_user', JSON.stringify(userData));
    } catch (err) {
      // If fetching me fails due to expired token
      clearAuthToken();
      setUser(null);
      setTokenState(null);
    }
  };

  const login = async (email: string, password: string) => {
    const res = await apiRequest<AuthResponse>('/auth/login', {
      method: 'POST',
      body: JSON.stringify({ email, password }),
    });

    setAuthToken(res.token);
    setTokenState(res.token);

    const userData: User = {
      id: res.id,
      email: res.email,
      fullName: res.fullName,
      role: res.role,
    };

    setUser(userData);
    localStorage.setItem('fraudshield_user', JSON.stringify(userData));
  };

  const register = async (data: {
    email: string;
    password: string;
    fullName: string;
    role: 'CONSUMER' | 'VENDOR';
    businessName?: string;
  }) => {
    const res = await apiRequest<AuthResponse>('/auth/register', {
      method: 'POST',
      body: JSON.stringify(data),
    });

    setAuthToken(res.token);
    setTokenState(res.token);

    const userData: User = {
      id: res.id,
      email: res.email,
      fullName: res.fullName,
      role: res.role,
      businessName: data.businessName,
    };

    setUser(userData);
    localStorage.setItem('fraudshield_user', JSON.stringify(userData));
  };

  const logout = () => {
    clearAuthToken();
    setUser(null);
    setTokenState(null);
    if (typeof window !== 'undefined') {
      window.location.href = '/login';
    }
  };

  return (
    <AuthContext.Provider value={{ user, token, isLoading, login, register, logout }}>
      {children}
    </AuthContext.Provider>
  );
}

export function useAuth() {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuth must be used within an AuthProvider');
  }
  return context;
}
