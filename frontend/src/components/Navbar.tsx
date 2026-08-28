'use client';

import Link from 'next/link';
import { ShieldCheck, ScanLine, FlaskConical, LayoutDashboard, UserCheck, Menu, X } from 'lucide-react';
import { useState } from 'react';

export function Navbar() {
  const [mobileMenuOpen, setMobileMenuOpen] = useState(false);

  return (
    <nav className="sticky top-0 z-40 border-b border-slate-200/80 bg-white/95 backdrop-blur-md">
      <div className="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">
        <div className="flex h-16 items-center justify-between">
          {/* Logo & Brand */}
          <Link href="/" className="flex items-center gap-2.5 group">
            <div className="flex h-10 w-10 items-center justify-center rounded-xl bg-emerald-600 text-white shadow-md shadow-emerald-600/20 transition-transform group-hover:scale-105">
              <ShieldCheck className="h-6 w-6" />
            </div>
            <div>
              <span className="text-xl font-extrabold tracking-tight text-slate-900">
                FraudShield<span className="text-emerald-600">.AI</span>
              </span>
              <p className="text-[10px] font-medium text-slate-500 uppercase tracking-widest -mt-1">
                Food Safety Intelligence
              </p>
            </div>
          </Link>

          {/* Desktop Navigation Links */}
          <div className="hidden md:flex md:items-center md:gap-8 text-sm font-semibold text-slate-600">
            <Link href="/" className="hover:text-emerald-600 transition-colors">
              Home
            </Link>
            <Link href="/scan/packaged" className="flex items-center gap-1.5 hover:text-emerald-600 transition-colors">
              <ScanLine className="h-4 w-4 text-sky-500" />
              Scan Product
            </Link>
            <Link href="/scan/testable" className="flex items-center gap-1.5 hover:text-emerald-600 transition-colors">
              <FlaskConical className="h-4 w-4 text-emerald-500" />
              Adulteration Test
            </Link>
            <Link href="/dashboard" className="flex items-center gap-1.5 hover:text-emerald-600 transition-colors">
              <LayoutDashboard className="h-4 w-4 text-indigo-500" />
              Dashboard
            </Link>
          </div>

          {/* Auth Actions */}
          <div className="hidden md:flex md:items-center md:gap-3">
            <Link
              href="/login"
              className="px-4 py-2 text-sm font-medium text-slate-700 hover:text-emerald-600 transition-colors"
            >
              Sign In
            </Link>
            <Link
              href="/register"
              className="flex items-center gap-1.5 rounded-lg bg-emerald-600 px-4 py-2 text-sm font-semibold text-white shadow-sm hover:bg-emerald-700 transition-all hover:shadow-emerald-600/20"
            >
              <UserCheck className="h-4 w-4" />
              Get Started
            </Link>
          </div>

          {/* Mobile menu button */}
          <div className="flex md:hidden">
            <button
              onClick={() => setMobileMenuOpen(!mobileMenuOpen)}
              className="inline-flex items-center justify-center rounded-lg p-2 text-slate-600 hover:bg-slate-100 hover:text-slate-900 focus:outline-none"
            >
              {mobileMenuOpen ? <X className="h-6 w-6" /> : <Menu className="h-6 w-6" />}
            </button>
          </div>
        </div>
      </div>

      {/* Mobile Menu Dropdown */}
      {mobileMenuOpen && (
        <div className="md:hidden border-b border-slate-200 bg-white px-4 pt-2 pb-4 space-y-2">
          <Link
            href="/"
            onClick={() => setMobileMenuOpen(false)}
            className="block rounded-md px-3 py-2 text-base font-medium text-slate-700 hover:bg-slate-50 hover:text-emerald-600"
          >
            Home
          </Link>
          <Link
            href="/scan/packaged"
            onClick={() => setMobileMenuOpen(false)}
            className="flex items-center gap-2 rounded-md px-3 py-2 text-base font-medium text-slate-700 hover:bg-slate-50 hover:text-emerald-600"
          >
            <ScanLine className="h-5 w-5 text-sky-500" />
            Scan Packaged Product
          </Link>
          <Link
            href="/scan/testable"
            onClick={() => setMobileMenuOpen(false)}
            className="flex items-center gap-2 rounded-md px-3 py-2 text-base font-medium text-slate-700 hover:bg-slate-50 hover:text-emerald-600"
          >
            <FlaskConical className="h-5 w-5 text-emerald-500" />
            Test Food Adulteration
          </Link>
          <Link
            href="/dashboard"
            onClick={() => setMobileMenuOpen(false)}
            className="flex items-center gap-2 rounded-md px-3 py-2 text-base font-medium text-slate-700 hover:bg-slate-50 hover:text-emerald-600"
          >
            <LayoutDashboard className="h-5 w-5 text-indigo-500" />
            Dashboard
          </Link>
          <div className="pt-2 border-t border-slate-100 flex flex-col gap-2">
            <Link
              href="/login"
              onClick={() => setMobileMenuOpen(false)}
              className="text-center rounded-md border border-slate-300 px-3 py-2 text-base font-medium text-slate-700 hover:bg-slate-50"
            >
              Sign In
            </Link>
            <Link
              href="/register"
              onClick={() => setMobileMenuOpen(false)}
              className="text-center rounded-md bg-emerald-600 px-3 py-2 text-base font-medium text-white hover:bg-emerald-700"
            >
              Register
            </Link>
          </div>
        </div>
      )}
    </nav>
  );
}
