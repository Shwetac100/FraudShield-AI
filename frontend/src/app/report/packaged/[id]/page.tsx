'use client';

import { use } from 'react';
import Link from 'next/link';
import { Navbar } from '@/components/Navbar';
import { Sidebar } from '@/components/Sidebar';
import { ScoreGauge } from '@/components/ScoreGauge';
import { mockPackagedReports } from '@/lib/mockData';
import {
  ShieldAlert,
  ShieldCheck,
  Info,
  CheckCircle2,
  AlertTriangle,
  ArrowLeft,
  Sparkles,
  ExternalLink,
} from 'lucide-react';

export default function PackagedReportPage({ params }: { params: Promise<{ id: string }> }) {
  const resolvedParams = use(params);
  const reportId = resolvedParams.id || 'scan-103';
  const report = mockPackagedReports[reportId] || mockPackagedReports['scan-103'];

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
                  {report.category}
                </span>
                <span className="text-xs text-slate-400">Scanned on {report.scanDate}</span>
              </div>
              <h1 className="text-2xl sm:text-3xl font-black text-slate-900">{report.productName}</h1>
              <p className="text-sm font-semibold text-slate-500">Brand: {report.brand}</p>
            </div>

            <div className="shrink-0 flex items-center gap-6 bg-slate-50 p-4 rounded-2xl border border-slate-100">
              <ScoreGauge score={report.qualityScore} size="md" label="Overall Food Score" />
            </div>
          </div>

          {/* Grid Layout for Detailed Analysis */}
          <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
            {/* Left 2 Columns: Ingredients & Additives */}
            <div className="lg:col-span-2 space-y-6">
              {/* Ingredient Breakdown */}
              <div className="rounded-2xl border border-slate-200 bg-white p-6 shadow-xs space-y-4">
                <h3 className="text-base font-extrabold text-slate-900 flex items-center justify-between">
                  <span>Ingredient Breakdown</span>
                  <span className="text-xs font-bold text-slate-400">
                    Total: {report.ingredientAnalysis.totalIngredients} items
                  </span>
                </h3>

                <div className="grid grid-cols-3 gap-3 text-center">
                  <div className="p-3 rounded-xl bg-emerald-50 border border-emerald-100">
                    <span className="text-lg font-black text-emerald-700">
                      {report.ingredientAnalysis.naturalCount}
                    </span>
                    <p className="text-[11px] font-semibold text-emerald-600">Natural</p>
                  </div>
                  <div className="p-3 rounded-xl bg-amber-50 border border-amber-100">
                    <span className="text-lg font-black text-amber-700">
                      {report.ingredientAnalysis.processedCount}
                    </span>
                    <p className="text-[11px] font-semibold text-amber-600">Processed</p>
                  </div>
                  <div className="p-3 rounded-xl bg-rose-50 border border-rose-100">
                    <span className="text-lg font-black text-rose-700">
                      {report.ingredientAnalysis.additiveCount}
                    </span>
                    <p className="text-[11px] font-semibold text-rose-600">Additives</p>
                  </div>
                </div>

                <div className="pt-2">
                  <p className="text-xs font-bold text-slate-500 uppercase tracking-wider mb-2">
                    Extracted Ingredients List
                  </p>
                  <div className="flex flex-wrap gap-1.5">
                    {report.ingredientAnalysis.ingredientsList.map((ing, i) => (
                      <span
                        key={i}
                        className="rounded-lg bg-slate-100 px-2.5 py-1 text-xs font-medium text-slate-700"
                      >
                        {ing}
                      </span>
                    ))}
                  </div>
                </div>
              </div>

              {/* Harmful Additives Flagged */}
              <div className="rounded-2xl border border-slate-200 bg-white p-6 shadow-xs space-y-4">
                <h3 className="text-base font-extrabold text-slate-900 flex items-center gap-2">
                  <ShieldAlert className="h-5 w-5 text-rose-600" />
                  Harmful / Suspicious Additives
                </h3>

                {report.harmfulAdditives.length === 0 ? (
                  <p className="text-xs text-slate-500">No high-risk harmful additives detected.</p>
                ) : (
                  <div className="space-y-3">
                    {report.harmfulAdditives.map((item, idx) => (
                      <div
                        key={idx}
                        className="p-4 rounded-xl border border-rose-100 bg-rose-50/40 space-y-1.5"
                      >
                        <div className="flex items-center justify-between">
                          <span className="text-sm font-bold text-rose-900 flex items-center gap-2">
                            {item.name}
                            {item.eNumber && (
                              <span className="rounded bg-rose-200 px-1.5 py-0.5 text-[10px] font-bold text-rose-800">
                                {item.eNumber}
                              </span>
                            )}
                          </span>
                          <span className="text-[10px] font-bold uppercase tracking-wider px-2 py-0.5 rounded bg-rose-200 text-rose-800">
                            {item.severity} SEVERITY
                          </span>
                        </div>
                        <p className="text-xs text-rose-800 leading-relaxed">{item.description}</p>
                      </div>
                    ))}
                  </div>
                )}
              </div>

              {/* Explainable AI Reasoning */}
              <div className="rounded-2xl border border-emerald-200 bg-emerald-50/30 p-6 shadow-xs space-y-4">
                <h3 className="text-base font-extrabold text-emerald-950 flex items-center gap-2">
                  <Sparkles className="h-5 w-5 text-emerald-600" />
                  Explainable AI Reasoning
                </h3>

                <p className="text-xs text-slate-700 font-medium leading-relaxed">
                  {report.explainableReasoning.summary}
                </p>

                <div className="space-y-2">
                  <p className="text-xs font-bold text-emerald-800 uppercase tracking-wider">
                    Positive Attributes
                  </p>
                  <ul className="space-y-1">
                    {report.explainableReasoning.positives.map((pos, i) => (
                      <li key={i} className="flex items-start gap-2 text-xs text-slate-700">
                        <CheckCircle2 className="h-4 w-4 text-emerald-600 shrink-0 mt-0.5" />
                        <span>{pos}</span>
                      </li>
                    ))}
                  </ul>
                </div>

                <div className="space-y-2 pt-2 border-t border-emerald-200/60">
                  <p className="text-xs font-bold text-amber-800 uppercase tracking-wider">
                    Key Concerns
                  </p>
                  <ul className="space-y-1">
                    {report.explainableReasoning.concerns.map((con, i) => (
                      <li key={i} className="flex items-start gap-2 text-xs text-slate-700">
                        <AlertTriangle className="h-4 w-4 text-amber-600 shrink-0 mt-0.5" />
                        <span>{con}</span>
                      </li>
                    ))}
                  </ul>
                </div>
              </div>
            </div>

            {/* Right Column: Nutrition & Healthier Alternatives */}
            <div className="space-y-6">
              {/* Nutrition Summary */}
              <div className="rounded-2xl border border-slate-200 bg-white p-6 shadow-xs space-y-4">
                <h3 className="text-base font-extrabold text-slate-900">Nutrition Summary</h3>

                <div className="space-y-2 text-xs divide-y divide-slate-100">
                  <div className="flex justify-between py-2">
                    <span className="text-slate-500 font-medium">Energy / Calories</span>
                    <span className="font-bold text-slate-800">{report.nutritionSummary.calories}</span>
                  </div>
                  <div className="flex justify-between py-2">
                    <span className="text-slate-500 font-medium">Added Sugars</span>
                    <span className="font-bold text-slate-800">{report.nutritionSummary.sugars}</span>
                  </div>
                  <div className="flex justify-between py-2">
                    <span className="text-slate-500 font-medium">Sodium Level</span>
                    <span className="font-bold text-slate-800">{report.nutritionSummary.sodium}</span>
                  </div>
                  <div className="flex justify-between py-2">
                    <span className="text-slate-500 font-medium">Trans Fats</span>
                    <span className="font-bold text-slate-800">{report.nutritionSummary.transFat}</span>
                  </div>
                  <div className="flex justify-between py-2">
                    <span className="text-slate-500 font-medium">Protein</span>
                    <span className="font-bold text-slate-800">{report.nutritionSummary.protein}</span>
                  </div>
                </div>
              </div>

              {/* Healthier Alternatives */}
              <div className="rounded-2xl border border-slate-200 bg-white p-6 shadow-xs space-y-4">
                <h3 className="text-base font-extrabold text-slate-900 flex items-center gap-2">
                  <Sparkles className="h-4 w-4 text-emerald-600" />
                  Healthier Swaps
                </h3>

                <div className="space-y-3">
                  {report.healthierAlternatives.map((alt) => (
                    <div
                      key={alt.id}
                      className="p-3.5 rounded-xl border border-slate-200 bg-slate-50 hover:bg-emerald-50/40 hover:border-emerald-200 transition-all space-y-1.5"
                    >
                      <div className="flex items-center justify-between">
                        <span className="text-xs font-bold text-slate-900">{alt.name}</span>
                        <span className="rounded-md bg-emerald-100 px-2 py-0.5 text-[10px] font-black text-emerald-800">
                          {alt.score}/100
                        </span>
                      </div>
                      <p className="text-[11px] font-medium text-slate-500">{alt.brand}</p>
                      <p className="text-xs text-slate-600 leading-relaxed">{alt.reason}</p>
                    </div>
                  ))}
                </div>
              </div>
            </div>
          </div>
        </main>
      </div>
    </div>
  );
}
