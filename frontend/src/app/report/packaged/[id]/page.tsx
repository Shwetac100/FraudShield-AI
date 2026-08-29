'use client';

import { use, useEffect, useState } from 'react';
import Link from 'next/link';
import { Navbar } from '@/components/Navbar';
import { Sidebar } from '@/components/Sidebar';
import { ScoreGauge } from '@/components/ScoreGauge';
import { RiskBadge } from '@/components/RiskBadge';
import { LoadingSpinner } from '@/components/LoadingSpinner';
import { apiRequest } from '@/lib/api';
import { ScanResponse } from '@/types';
import {
  ShieldAlert,
  ShieldCheck,
  CheckCircle2,
  AlertTriangle,
  ArrowLeft,
  Sparkles,
  AlertCircle,
} from 'lucide-react';

export default function PackagedReportPage({ params }: { params: Promise<{ id: string }> }) {
  const resolvedParams = use(params);
  const scanId = resolvedParams.id;

  const [scan, setScan] = useState<ScanResponse | null>(null);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    async function loadReport() {
      try {
        setIsLoading(true);
        const data = await apiRequest<ScanResponse>(`/scans/${scanId}`);
        setScan(data);
      } catch (err: any) {
        setError(err?.message || 'Failed to load report from server.');
      } finally {
        setIsLoading(false);
      }
    }
    if (scanId) {
      loadReport();
    }
  }, [scanId]);

  if (isLoading) {
    return (
      <div className="min-h-screen bg-slate-50 flex flex-col font-sans">
        <Navbar />
        <div className="flex-1 flex max-w-7xl w-full mx-auto p-8 items-center justify-center">
          <LoadingSpinner label="Fetching scan analysis report..." />
        </div>
      </div>
    );
  }

  if (error || !scan) {
    return (
      <div className="min-h-screen bg-slate-50 flex flex-col font-sans">
        <Navbar />
        <div className="flex-1 flex max-w-7xl w-full mx-auto p-8">
          <Sidebar />
          <main className="flex-1 p-8 space-y-4">
            <Link href="/dashboard" className="inline-flex items-center gap-1.5 text-xs font-bold text-slate-500 hover:text-slate-800">
              <ArrowLeft className="h-4 w-4" /> Back to Dashboard
            </Link>
            <div className="p-6 rounded-2xl bg-rose-50 border border-rose-200 text-rose-700 flex items-center gap-3">
              <AlertCircle className="h-6 w-6 shrink-0" />
              <div>
                <h3 className="font-bold text-sm">Report Unavailable</h3>
                <p className="text-xs">{error || 'Scan result not found'}</p>
              </div>
            </div>
          </main>
        </div>
      </div>
    );
  }

  // Convert risk level to score
  let score = 85;
  if (scan.riskLevel === 'MEDIUM') score = 65;
  if (scan.riskLevel === 'HIGH') score = 40;
  if (scan.riskLevel === 'CRITICAL') score = 15;

  const details = scan.packagedDetails;
  const ingredientsArray = details?.ingredientsText
    ? details.ingredientsText.split(',').map((s) => s.trim()).filter(Boolean)
    : [];

  const harmfulList = details?.detectedHarmfulAdditives
    ? details.detectedHarmfulAdditives.split(',').map((s) => s.trim()).filter(Boolean)
    : [];

  const eNumbersList = details?.detectedENumbers
    ? details.detectedENumbers.split(',').map((s) => s.trim()).filter(Boolean)
    : [];

  return (
    <div className="min-h-screen bg-slate-50 flex flex-col font-sans">
      <Navbar />

      <div className="flex-1 flex max-w-7xl w-full mx-auto">
        <Sidebar />

        <main className="flex-1 p-4 sm:p-6 lg:p-8 space-y-8 overflow-y-auto">
          {/* Top navigation */}
          <Link
            href="/dashboard"
            className="inline-flex items-center gap-1.5 text-xs font-bold text-slate-500 hover:text-slate-800 transition-colors"
          >
            <ArrowLeft className="h-4 w-4" />
            Back to Dashboard
          </Link>

          {/* Product Header Card */}
          <div className="rounded-3xl border border-slate-200/80 bg-white p-6 sm:p-8 shadow-sm flex flex-col md:flex-row items-start md:items-center justify-between gap-6">
            <div className="space-y-2">
              <div className="flex items-center gap-2">
                <span className="rounded-full bg-slate-100 px-3 py-1 text-xs font-bold text-slate-700">
                  {scan.scanType}
                </span>
                <span className="text-xs text-slate-400">Scanned on {new Date(scan.createdAt).toLocaleDateString()}</span>
                <RiskBadge level={scan.riskLevel} />
              </div>
              <h1 className="text-2xl sm:text-3xl font-black text-slate-900">{scan.productName}</h1>
              <p className="text-xs text-slate-500 font-mono">Scan ID: #{scan.id}</p>
            </div>

            <div className="shrink-0 flex items-center gap-6 bg-slate-50 p-4 rounded-2xl border border-slate-100">
              <ScoreGauge score={score} size="md" label="Safety Index" />
            </div>
          </div>

          {/* Grid Layout for Detailed Analysis */}
          <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
            {/* Left 2 Columns: Ingredients & Additives */}
            <div className="lg:col-span-2 space-y-6">
              {/* Ingredient Breakdown */}
              <div className="rounded-2xl border border-slate-200 bg-white p-6 shadow-xs space-y-4">
                <h3 className="text-base font-extrabold text-slate-900 flex items-center justify-between">
                  <span>Extracted Ingredients</span>
                  <span className="text-xs font-bold text-slate-400">
                    Total: {ingredientsArray.length} detected
                  </span>
                </h3>

                <div className="pt-2">
                  <div className="flex flex-wrap gap-1.5">
                    {ingredientsArray.length > 0 ? (
                      ingredientsArray.map((ing, i) => (
                        <span
                          key={i}
                          className="rounded-lg bg-slate-100 px-2.5 py-1 text-xs font-medium text-slate-700"
                        >
                          {ing}
                        </span>
                      ))
                    ) : (
                      <p className="text-xs text-slate-500">No ingredients text extracted.</p>
                    )}
                  </div>
                </div>
              </div>

              {/* Harmful Additives Flagged */}
              <div className="rounded-2xl border border-slate-200 bg-white p-6 shadow-xs space-y-4">
                <h3 className="text-base font-extrabold text-slate-900 flex items-center gap-2">
                  <ShieldAlert className="h-5 w-5 text-rose-600" />
                  Detected E-Numbers & Harmful Additives
                </h3>

                {harmfulList.length === 0 && eNumbersList.length === 0 ? (
                  <p className="text-xs text-emerald-600 font-semibold">No high-risk harmful additives or flagged E-numbers detected.</p>
                ) : (
                  <div className="space-y-3">
                    {eNumbersList.map((eNum, idx) => (
                      <div key={idx} className="p-3.5 rounded-xl border border-rose-100 bg-rose-50/40 flex items-center justify-between">
                        <span className="text-xs font-bold text-rose-900">Additive E-Number: {eNum}</span>
                        <span className="text-[10px] font-bold px-2 py-0.5 rounded bg-rose-200 text-rose-800">FLAGGED</span>
                      </div>
                    ))}
                    {harmfulList.map((item, idx) => (
                      <div key={idx} className="p-3.5 rounded-xl border border-amber-100 bg-amber-50/40 flex items-center justify-between">
                        <span className="text-xs font-bold text-amber-900">Harmful Compound: {item}</span>
                        <span className="text-[10px] font-bold px-2 py-0.5 rounded bg-amber-200 text-amber-800">WARNING</span>
                      </div>
                    ))}
                  </div>
                )}
              </div>

              {/* Explainable AI Reasoning */}
              <div className="rounded-2xl border border-emerald-200 bg-emerald-50/30 p-6 shadow-xs space-y-4">
                <h3 className="text-base font-extrabold text-emerald-950 flex items-center gap-2">
                  <Sparkles className="h-5 w-5 text-emerald-600" />
                  Safety Assessment & Explanation
                </h3>

                <p className="text-xs text-slate-700 font-medium leading-relaxed">
                  {scan.riskExplanation || scan.summaryResult}
                </p>

                <div className="space-y-2">
                  <p className="text-xs font-bold text-emerald-800 uppercase tracking-wider">
                    Summary Verdict
                  </p>
                  <p className="text-xs text-slate-700 font-semibold">{scan.summaryResult}</p>
                </div>
              </div>
            </div>

            {/* Right Column: Nutrition */}
            <div className="space-y-6">
              <div className="rounded-2xl border border-slate-200 bg-white p-6 shadow-xs space-y-4">
                <h3 className="text-base font-extrabold text-slate-900">Nutritional Information</h3>
                <p className="text-xs text-slate-600 leading-relaxed font-mono bg-slate-50 p-3 rounded-xl border border-slate-100">
                  {details?.nutritionalInfo || 'Nutritional information not explicitly provided in OCR scan.'}
                </p>
              </div>
            </div>
          </div>
        </main>
      </div>
    </div>
  );
}
