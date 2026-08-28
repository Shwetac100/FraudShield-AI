'use client';

import Link from 'next/link';
import { Navbar } from '@/components/Navbar';
import { Sidebar } from '@/components/Sidebar';
import { ScanCard } from '@/components/ScanCard';
import { mockScanHistory } from '@/lib/mockData';
import {
  ScanLine,
  FlaskConical,
  ShieldCheck,
  PlusCircle,
  TrendingUp,
  AlertOctagon,
  History,
} from 'lucide-react';

export default function DashboardPage() {
  return (
    <div className="min-h-screen bg-slate-50 flex flex-col font-sans">
      <Navbar />

      <div className="flex-1 flex max-w-7xl w-full mx-auto">
        <Sidebar />

        <main className="flex-1 p-4 sm:p-6 lg:p-8 space-y-8 overflow-y-auto">
          {/* Welcome Card */}
          <div className="relative overflow-hidden rounded-3xl bg-gradient-to-r from-slate-900 via-slate-800 to-emerald-950 p-6 sm:p-8 text-white shadow-xl">
            <div className="absolute top-0 right-0 p-8 opacity-10">
              <ShieldCheck className="h-48 w-48 text-emerald-400" />
            </div>
            <div className="relative space-y-3 max-w-xl">
              <span className="inline-block rounded-full bg-emerald-500/20 border border-emerald-500/30 px-3 py-1 text-xs font-semibold text-emerald-300">
                Consumer Safety Portal
              </span>
              <h1 className="text-2xl sm:text-3xl font-extrabold tracking-tight">
                Welcome back, Alex! 👋
              </h1>
              <p className="text-xs sm:text-sm text-slate-300 leading-relaxed">
                Your food safety score is in healthy range. You have scanned 12 items this month with 1 adulteration alert flagged.
              </p>
            </div>

            {/* Quick Metrics */}
            <div className="mt-6 grid grid-cols-2 sm:grid-cols-3 gap-4 border-t border-slate-700/60 pt-6">
              <div>
                <span className="text-2xl font-black text-white">12</span>
                <p className="text-xs text-slate-400">Total Scans</p>
              </div>
              <div>
                <span className="text-2xl font-black text-emerald-400">82%</span>
                <p className="text-xs text-slate-400">Avg Quality Score</p>
              </div>
              <div>
                <span className="text-2xl font-black text-rose-400">1</span>
                <p className="text-xs text-slate-400">Alert Flagged</p>
              </div>
            </div>
          </div>

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
                    <p className="text-xs text-slate-500">Guided home reaction test for Ghee, Honey & Milk</p>
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
              <span className="text-xs font-semibold text-slate-500">Showing last 4 scans</span>
            </div>

            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
              {mockScanHistory.map((item) => (
                <ScanCard key={item.id} item={item} />
              ))}
            </div>
          </section>
        </main>
      </div>
    </div>
  );
}
