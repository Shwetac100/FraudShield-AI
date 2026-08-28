'use client';

import { use } from 'react';
import Link from 'next/link';
import { Navbar } from '@/components/Navbar';
import { Sidebar } from '@/components/Sidebar';
import { RiskBadge } from '@/components/RiskBadge';
import { mockAdulterationReports } from '@/lib/mockData';
import {
  ShieldAlert,
  ShieldCheck,
  AlertOctagon,
  ArrowLeft,
  Sparkles,
  Info,
  CheckCircle,
  AlertTriangle,
} from 'lucide-react';

export default function AdulterationReportPage({ params }: { params: Promise<{ id: string }> }) {
  const resolvedParams = use(params);
  const reportId = resolvedParams.id || 'scan-102';
  const report = mockAdulterationReports[reportId] || mockAdulterationReports['scan-102'];

  const isHighRisk = report.riskLevel === 'HIGH' || report.riskLevel === 'CRITICAL';

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

          {/* Header Card */}
          <div className="rounded-3xl border border-slate-200/80 bg-white p-6 sm:p-8 shadow-sm flex flex-col md:flex-row items-start md:items-center justify-between gap-6">
            <div className="space-y-2">
              <div className="flex items-center gap-2">
                <span className="rounded-full bg-slate-100 px-3 py-1 text-xs font-bold text-slate-700">
                  {report.category}
                </span>
                <span className="text-xs text-slate-400">Tested on {report.testDate}</span>
              </div>
              <h1 className="text-2xl sm:text-3xl font-black text-slate-900">{report.foodName}</h1>
              <div className="flex items-center gap-3 pt-1">
                <RiskBadge level={report.riskLevel} />
                <span className="text-xs font-bold text-slate-500">
                  AI Confidence: <strong className="text-slate-800">{report.confidenceScore}%</strong>
                </span>
              </div>
            </div>

            {/* Risk Visual Card */}
            <div
              className={`p-6 rounded-2xl border text-center space-y-2 min-w-[220px] ${
                isHighRisk
                  ? 'border-rose-200 bg-rose-50 text-rose-900'
                  : 'border-emerald-200 bg-emerald-50 text-emerald-900'
              }`}
            >
              {isHighRisk ? (
                <AlertOctagon className="h-10 w-10 text-rose-600 mx-auto" />
              ) : (
                <ShieldCheck className="h-10 w-10 text-emerald-600 mx-auto" />
              )}
              <h3 className="text-lg font-black">{isHighRisk ? 'Adulteration Detected' : 'Sample Appears Pure'}</h3>
              <p className="text-xs opacity-80">{isHighRisk ? 'Non-compliant product' : 'Passed rapid home screening'}</p>
            </div>
          </div>

          {/* Key Findings Grid */}
          <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
            {/* Left 2 Columns: Adulterants & AI Observations */}
            <div className="lg:col-span-2 space-y-6">
              {/* Detected Adulterants */}
              <div className="rounded-2xl border border-slate-200 bg-white p-6 shadow-xs space-y-4">
                <h3 className="text-base font-extrabold text-slate-900 flex items-center gap-2">
                  <ShieldAlert className={`h-5 w-5 ${isHighRisk ? 'text-rose-600' : 'text-emerald-600'}`} />
                  Adulterant Breakdown
                </h3>

                {report.detectedAdulterants.length === 0 ? (
                  <div className="p-4 rounded-xl bg-emerald-50 border border-emerald-100 flex items-center gap-3 text-emerald-800 text-xs font-semibold">
                    <CheckCircle className="h-5 w-5 text-emerald-600 shrink-0" />
                    <span>Zero adulterants or foreign fillers were identified in this sample.</span>
                  </div>
                ) : (
                  <div className="space-y-2">
                    <p className="text-xs text-slate-500 font-medium">The following substances were flagged:</p>
                    <div className="space-y-2">
                      {report.detectedAdulterants.map((item, idx) => (
                        <div
                          key={idx}
                          className="flex items-center justify-between p-3.5 rounded-xl border border-rose-200 bg-rose-50/50 text-xs font-bold text-rose-900"
                        >
                          <span className="flex items-center gap-2">
                            <AlertTriangle className="h-4 w-4 text-rose-600" />
                            {item}
                          </span>
                          <span className="rounded bg-rose-200 px-2 py-0.5 text-[10px] uppercase font-black text-rose-800">
                            Positive Marker
                          </span>
                        </div>
                      ))}
                    </div>
                  </div>
                )}
              </div>

              {/* AI Vision & Spectral Observations */}
              <div className="rounded-2xl border border-slate-200 bg-white p-6 shadow-xs space-y-4">
                <h3 className="text-base font-extrabold text-slate-900 flex items-center gap-2">
                  <Sparkles className="h-5 w-5 text-indigo-600" />
                  AI Vision & Chemical Observations
                </h3>

                <div className="space-y-3 text-xs">
                  <div className="p-3.5 rounded-xl bg-slate-50 border border-slate-100 space-y-1">
                    <span className="font-bold text-slate-800 uppercase text-[10px] tracking-wider">
                      Visual Layer Spectrum
                    </span>
                    <p className="text-slate-600 leading-relaxed">{report.aiObservations.visualMarker}</p>
                  </div>

                  <div className="p-3.5 rounded-xl bg-slate-50 border border-slate-100 space-y-1">
                    <span className="font-bold text-slate-800 uppercase text-[10px] tracking-wider">
                      Chemical Reaction Indicator
                    </span>
                    <p className="text-slate-600 leading-relaxed">{report.aiObservations.chemicalIndicator}</p>
                  </div>

                  <div className="p-3.5 rounded-xl bg-slate-50 border border-slate-100 space-y-1">
                    <span className="font-bold text-slate-800 uppercase text-[10px] tracking-wider">
                      Texture & Consistency
                    </span>
                    <p className="text-slate-600 leading-relaxed">{report.aiObservations.textureNote}</p>
                  </div>
                </div>
              </div>
            </div>

            {/* Right Column: Next Actions & Disclaimer */}
            <div className="space-y-6">
              {/* Recommended Next Action */}
              <div
                className={`rounded-2xl border p-6 shadow-xs space-y-3 ${
                  isHighRisk
                    ? 'border-rose-200 bg-rose-600 text-white'
                    : 'border-emerald-200 bg-emerald-600 text-white'
                }`}
              >
                <h3 className="text-base font-extrabold flex items-center gap-2">
                  <Info className="h-5 w-5" />
                  Recommended Action
                </h3>
                <p className="text-xs leading-relaxed opacity-95">{report.recommendedNextAction}</p>
              </div>

              {/* Legal / AI Disclaimer */}
              <div className="rounded-2xl border border-slate-200 bg-white p-6 shadow-xs space-y-3 text-xs text-slate-500">
                <h4 className="font-bold text-slate-700 uppercase tracking-wider text-[11px]">
                  Safety & Method Disclaimer
                </h4>
                <p className="leading-relaxed">{report.disclaimer}</p>
              </div>
            </div>
          </div>
        </main>
      </div>
    </div>
  );
}
