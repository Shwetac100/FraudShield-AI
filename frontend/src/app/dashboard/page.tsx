'use client';

import { useEffect, useState } from 'react';
import Link from 'next/link';
import { Navbar } from '@/components/Navbar';
import { Sidebar } from '@/components/Sidebar';
import { ScanCard } from '@/components/ScanCard';
import { LoadingSpinner } from '@/components/LoadingSpinner';
import { useAuth } from '@/context/AuthContext';
import { apiRequest } from '@/lib/api';
import { ScanResponse, VendorDashboardSummary, ScanHistoryItem } from '@/types';
import {
  ScanLine,
  FlaskConical,
  ShieldCheck,
  PlusCircle,
  History,
  Store,
  CheckCircle2,
  AlertOctagon,
  Award,
} from 'lucide-react';

export default function DashboardPage() {
  const { user } = useAuth();
  const [scans, setScans] = useState<ScanResponse[]>([]);
  const [vendorSummary, setVendorSummary] = useState<VendorDashboardSummary | null>(null);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    async function loadDashboardData() {
      try {
        setIsLoading(true);
        if (user?.role === 'VENDOR') {
          const summary = await apiRequest<VendorDashboardSummary>('/vendor/dashboard');
          setVendorSummary(summary);
          setScans(summary.recentScans || []);
        } else {
          const userScans = await apiRequest<ScanResponse[]>('/scans');
          setScans(userScans || []);
        }
      } catch (err) {
        // Fallback or unauthenticated state handling
      } finally {
        setIsLoading(false);
      }
    }

    loadDashboardData();
  }, [user]);

  const scanHistoryItems: ScanHistoryItem[] = scans.map((s) => ({
    id: String(s.id),
    title: s.productName || (s.scanType === 'PACKAGED' ? 'Packaged Food Scan' : 'Adulteration Test'),
    type: s.scanType,
    date: new Date(s.createdAt).toLocaleDateString(),
    status: s.riskLevel === 'LOW' ? 'Completed' : 'Flagged',
    riskLevel: s.riskLevel,
    summary: s.summaryResult || s.riskExplanation,
  }));

  const totalCount = vendorSummary ? vendorSummary.totalScans : scans.length;
  const passedCount = vendorSummary
    ? vendorSummary.passedScans
    : scans.filter((s) => s.riskLevel === 'LOW').length;
  const flaggedCount = vendorSummary
    ? vendorSummary.flaggedScans
    : scans.filter((s) => s.riskLevel === 'HIGH' || s.riskLevel === 'CRITICAL').length;

  return (
    <div className="min-h-screen bg-slate-50 flex flex-col font-sans">
      <Navbar />

      <div className="flex-1 flex max-w-7xl w-full mx-auto">
        <Sidebar />

        <main className="flex-1 p-4 sm:p-6 lg:p-8 space-y-8 overflow-y-auto">
          {/* Welcome Header */}
          <div className="relative overflow-hidden rounded-3xl bg-gradient-to-r from-slate-900 via-slate-800 to-emerald-950 p-6 sm:p-8 text-white shadow-xl">
            <div className="absolute top-0 right-0 p-8 opacity-10">
              <ShieldCheck className="h-48 w-48 text-emerald-400" />
            </div>
            <div className="relative space-y-3 max-w-xl">
              <span className="inline-block rounded-full bg-emerald-500/20 border border-emerald-500/30 px-3 py-1 text-xs font-semibold text-emerald-300">
                {user?.role === 'VENDOR' ? 'Vendor Portal & Quality Assurance' : 'Consumer Safety Portal'}
              </span>
              <h1 className="text-2xl sm:text-3xl font-extrabold tracking-tight">
                Welcome back, {user ? user.fullName : 'Guest'}! 👋
              </h1>
              <p className="text-xs sm:text-sm text-slate-300 leading-relaxed">
                {user?.role === 'VENDOR'
                  ? `Vendor Store: ${user.businessName || vendorSummary?.businessName || 'Registered Store'}`
                  : 'Track your scans, verify food safety, and check adulteration test results in real-time.'}
              </p>
            </div>

            {/* Metrics */}
            <div className="mt-6 grid grid-cols-2 sm:grid-cols-4 gap-4 border-t border-slate-700/60 pt-6">
              <div>
                <span className="text-2xl font-black text-white">{totalCount || 0}</span>
                <p className="text-xs text-slate-400">Total Scans</p>
              </div>
              <div>
                <span className="text-2xl font-black text-emerald-400">{passedCount || 0}</span>
                <p className="text-xs text-slate-400">Passed / Safe Scans</p>
              </div>
              <div>
                <span className="text-2xl font-black text-rose-400">{flaggedCount || 0}</span>
                <p className="text-xs text-slate-400">High Risk / Flagged</p>
              </div>
              {user?.role === 'VENDOR' && (
                <div>
                  <span className="text-2xl font-black text-amber-400">
                    {vendorSummary?.qualityRating ? `${vendorSummary.qualityRating}/5.0` : '5.0/5.0'}
                  </span>
                  <p className="text-xs text-slate-400">Store Quality Rating</p>
                </div>
              )}
            </div>
          </div>

          {/* VENDOR WIDGET (Part 7: If logged-in role == VENDOR, show vendor metrics, else hidden) */}
          {user?.role === 'VENDOR' && vendorSummary && (
            <section className="p-6 rounded-2xl bg-white border border-slate-200 shadow-sm space-y-4">
              <div className="flex items-center gap-2">
                <Store className="h-5 w-5 text-emerald-600" />
                <h2 className="text-base font-bold text-slate-900">Vendor Compliance Dashboard</h2>
              </div>
              <div className="grid grid-cols-1 sm:grid-cols-3 gap-4">
                <div className="p-4 rounded-xl bg-slate-50 border border-slate-100 flex items-center gap-3">
                  <Award className="h-8 w-8 text-emerald-600" />
                  <div>
                    <span className="text-lg font-black text-slate-900">
                      {vendorSummary.compliancePercentage || 100}%
                    </span>
                    <p className="text-xs text-slate-500">Stock Compliance Score</p>
                  </div>
                </div>
                <div className="p-4 rounded-xl bg-slate-50 border border-slate-100 flex items-center gap-3">
                  <CheckCircle2 className="h-8 w-8 text-emerald-600" />
                  <div>
                    <span className="text-lg font-black text-slate-900">{vendorSummary.passedScans}</span>
                    <p className="text-xs text-slate-500">Verified Safe Batches</p>
                  </div>
                </div>
                <div className="p-4 rounded-xl bg-slate-50 border border-slate-100 flex items-center gap-3">
                  <AlertOctagon className="h-8 w-8 text-rose-600" />
                  <div>
                    <span className="text-lg font-black text-slate-900">{vendorSummary.flaggedScans}</span>
                    <p className="text-xs text-slate-500">Flagged Supplier Items</p>
                  </div>
                </div>
              </div>
            </section>
          )}

          {/* Quick Actions */}
          <section className="space-y-4">
            <h2 className="text-lg font-extrabold text-slate-900 flex items-center gap-2">
              <PlusCircle className="h-5 w-5 text-emerald-600" />
              Quick Actions
            </h2>

            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
              <Link
                href="/scan/packaged"
                className="group flex items-center justify-between p-5 rounded-2xl border border-slate-200 bg-white hover:border-sky-500 hover:shadow-md transition-all"
              >
                <div className="flex items-center gap-4">
                  <div className="flex h-12 w-12 items-center justify-center rounded-xl bg-sky-100 text-sky-600 group-hover:scale-105 transition-transform">
                    <ScanLine className="h-6 w-6" />
                  </div>
                  <div>
                    <h3 className="text-base font-bold text-slate-900">Scan Packaged Food</h3>
                    <p className="text-xs text-slate-500">Upload product labels & nutrition tags</p>
                  </div>
                </div>
                <span className="text-xs font-bold text-sky-600 group-hover:translate-x-1 transition-transform">
                  Start Scan →
                </span>
              </Link>

              <Link
                href="/scan/testable"
                className="group flex items-center justify-between p-5 rounded-2xl border border-slate-200 bg-white hover:border-emerald-500 hover:shadow-md transition-all"
              >
                <div className="flex items-center gap-4">
                  <div className="flex h-12 w-12 items-center justify-center rounded-xl bg-emerald-100 text-emerald-600 group-hover:scale-105 transition-transform">
                    <FlaskConical className="h-6 w-6" />
                  </div>
                  <div>
                    <h3 className="text-base font-bold text-slate-900">Test Food Adulteration</h3>
                    <p className="text-xs text-slate-500">Guided home reaction test protocol</p>
                  </div>
                </div>
                <span className="text-xs font-bold text-emerald-600 group-hover:translate-x-1 transition-transform">
                  Start Test →
                </span>
              </Link>
            </div>
          </section>

          {/* Recent Scans & Scan History */}
          <section id="history" className="space-y-4 pt-2">
            <div className="flex items-center justify-between">
              <h2 className="text-lg font-extrabold text-slate-900 flex items-center gap-2">
                <History className="h-5 w-5 text-indigo-600" />
                Recent Scans & Test History
              </h2>
              <span className="text-xs font-semibold text-slate-500">
                {isLoading ? 'Loading history...' : `${scanHistoryItems.length} records`}
              </span>
            </div>

            {isLoading ? (
              <LoadingSpinner label="Retrieving live scan history from backend..." />
            ) : scanHistoryItems.length === 0 ? (
              <div className="p-8 rounded-2xl border border-slate-200 bg-white text-center space-y-2">
                <p className="text-sm font-bold text-slate-700">No scan history found</p>
                <p className="text-xs text-slate-500">
                  Perform your first packaged scan or adulteration test to see real-time results here.
                </p>
              </div>
            ) : (
              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                {scanHistoryItems.map((item) => (
                  <ScanCard key={item.id} item={item} />
                ))}
              </div>
            )}
          </section>
        </main>
      </div>
    </div>
  );
}
