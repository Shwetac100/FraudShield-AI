import Link from 'next/link';
import { Navbar } from '@/components/Navbar';
import {
  ScanLine,
  FlaskConical,
  ShieldCheck,
  Zap,
  Microscope,
  CheckCircle2,
  ArrowRight,
  BarChart3,
  Search,
  Sparkles,
} from 'lucide-react';

export default function Home() {
  return (
    <div className="min-h-screen bg-slate-50 flex flex-col font-sans">
      <Navbar />

      {/* Hero Section */}
      <section className="relative overflow-hidden bg-gradient-to-b from-slate-900 via-slate-800 to-slate-900 text-white pt-20 pb-28">
        <div className="absolute inset-0 opacity-20 bg-[radial-gradient(#10b981_1px,transparent_1px)] [background-size:16px_16px]" />
        
        <div className="relative mx-auto max-w-7xl px-4 sm:px-6 lg:px-8 text-center space-y-8">
          <div className="inline-flex items-center gap-2 rounded-full bg-emerald-500/10 border border-emerald-500/30 px-4 py-1.5 text-xs font-semibold text-emerald-400">
            <Sparkles className="h-4 w-4 text-emerald-400" />
            <span>AI-Powered Food Safety & Quality Screening</span>
          </div>

          <h1 className="text-4xl sm:text-6xl font-black tracking-tight text-white max-w-4xl mx-auto leading-tight">
            Protecting What You Eat with <span className="text-transparent bg-clip-text bg-gradient-to-r from-emerald-400 to-teal-300">Intelligent Analysis</span>
          </h1>

          <p className="text-slate-300 text-lg sm:text-xl max-w-2xl mx-auto font-normal leading-relaxed">
            FraudShield AI uses advanced computer vision and regulatory benchmarks to scan packaged food labels and guide rapid home adulteration tests.
          </p>

          {/* Primary Two Scan Options */}
          <div className="pt-6 grid grid-cols-1 md:grid-cols-2 gap-6 max-w-3xl mx-auto text-left">
            {/* Option 1: Packaged Product */}
            <Link
              href="/scan/packaged"
              className="group relative rounded-3xl bg-slate-800/80 border border-slate-700/80 p-8 hover:border-sky-500/80 hover:bg-slate-800 transition-all shadow-xl hover:shadow-sky-500/10"
            >
              <div className="flex items-center justify-between mb-4">
                <div className="flex h-14 w-14 items-center justify-center rounded-2xl bg-sky-500/20 text-sky-400 border border-sky-500/30">
                  <ScanLine className="h-7 w-7" />
                </div>
                <span className="flex h-8 w-8 items-center justify-center rounded-full bg-slate-700 text-slate-300 group-hover:bg-sky-500 group-hover:text-white transition-all">
                  <ArrowRight className="h-4 w-4" />
                </span>
              </div>
              <h3 className="text-xl font-bold text-white mb-2">Scan Packaged Product</h3>
              <p className="text-sm text-slate-400 leading-relaxed">
                Upload front & back product photos to extract ingredient health risks, score overall quality, and flag harmful E-additives.
              </p>
            </Link>

            {/* Option 2: Testable Food Scan */}
            <Link
              href="/scan/testable"
              className="group relative rounded-3xl bg-slate-800/80 border border-slate-700/80 p-8 hover:border-emerald-500/80 hover:bg-slate-800 transition-all shadow-xl hover:shadow-emerald-500/10"
            >
              <div className="flex items-center justify-between mb-4">
                <div className="flex h-14 w-14 items-center justify-center rounded-2xl bg-emerald-500/20 text-emerald-400 border border-emerald-500/30">
                  <FlaskConical className="h-7 w-7" />
                </div>
                <span className="flex h-8 w-8 items-center justify-center rounded-full bg-slate-700 text-slate-300 group-hover:bg-emerald-500 group-hover:text-white transition-all">
                  <ArrowRight className="h-4 w-4" />
                </span>
              </div>
              <h3 className="text-xl font-bold text-white mb-2">Test Food Adulteration</h3>
              <p className="text-sm text-slate-400 leading-relaxed">
                Select raw staples like Ghee, Honey, or Milk for guided step-by-step chemical reaction testing and rapid AI risk scoring.
              </p>
            </Link>
          </div>
        </div>
      </section>

      {/* Workflow Section */}
      <section className="py-20 bg-white">
        <div className="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">
          <div className="text-center space-y-3 mb-16">
            <h2 className="text-xs font-bold uppercase tracking-widest text-emerald-600">3-Step Process</h2>
            <p className="text-3xl sm:text-4xl font-extrabold text-slate-900">How FraudShield AI Works</p>
            <p className="text-slate-600 max-w-xl mx-auto text-sm">
              Instant non-destructive analysis designed for everyday consumers and small business food vendors.
            </p>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
            <div className="rounded-2xl border border-slate-200 bg-slate-50/50 p-8 relative">
              <div className="flex h-12 w-12 items-center justify-center rounded-xl bg-emerald-600 text-white font-bold text-lg mb-6">
                1
              </div>
              <h3 className="text-lg font-bold text-slate-900 mb-2">Snap or Select Food</h3>
              <p className="text-sm text-slate-600 leading-relaxed">
                Capture high-resolution photos of food product labels or select a raw item for adulteration verification.
              </p>
            </div>

            <div className="rounded-2xl border border-slate-200 bg-slate-50/50 p-8 relative">
              <div className="flex h-12 w-12 items-center justify-center rounded-xl bg-emerald-600 text-white font-bold text-lg mb-6">
                2
              </div>
              <h3 className="text-lg font-bold text-slate-900 mb-2">Guided Verification</h3>
              <p className="text-sm text-slate-600 leading-relaxed">
                Follow our step-by-step chemical or visual reaction tests with clear material lists and countdown guidance.
              </p>
            </div>

            <div className="rounded-2xl border border-slate-200 bg-slate-50/50 p-8 relative">
              <div className="flex h-12 w-12 items-center justify-center rounded-xl bg-emerald-600 text-white font-bold text-lg mb-6">
                3
              </div>
              <h3 className="text-lg font-bold text-slate-900 mb-2">Instant Health & Risk Insights</h3>
              <p className="text-sm text-slate-600 leading-relaxed">
                Receive an explainable 0-100 Quality Score, detected adulterants, risk breakdown, and clean alternative recommendations.
              </p>
            </div>
          </div>
        </div>
      </section>

      {/* Key Features Grid */}
      <section className="py-20 bg-slate-900 text-white">
        <div className="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">
          <div className="text-center space-y-3 mb-16">
            <h2 className="text-xs font-bold uppercase tracking-widest text-emerald-400">Core Features</h2>
            <p className="text-3xl sm:text-4xl font-extrabold text-white">Built for Total Transparency</p>
          </div>

          <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6">
            <div className="rounded-2xl border border-slate-800 bg-slate-800/50 p-6 space-y-3">
              <Zap className="h-8 w-8 text-sky-400" />
              <h4 className="text-base font-bold text-white">OCR Label Extraction</h4>
              <p className="text-xs text-slate-400 leading-relaxed">
                Automatically reads messy ingredients lists and nutrition facts from packaging photos.
              </p>
            </div>

            <div className="rounded-2xl border border-slate-800 bg-slate-800/50 p-6 space-y-3">
              <Microscope className="h-8 w-8 text-emerald-400" />
              <h4 className="text-base font-bold text-white">Adulteration Reaction AI</h4>
              <p className="text-xs text-slate-400 leading-relaxed">
                Detects color separation and precipitate markers in Baudouin or Iodine tests.
              </p>
            </div>

            <div className="rounded-2xl border border-slate-800 bg-slate-800/50 p-6 space-y-3">
              <BarChart3 className="h-8 w-8 text-indigo-400" />
              <h4 className="text-base font-bold text-white">Explainable Risk Scores</h4>
              <p className="text-xs text-slate-400 leading-relaxed">
                Clear scientific reasoning behind every score without confusing technical jargon.
              </p>
            </div>

            <div className="rounded-2xl border border-slate-800 bg-slate-800/50 p-6 space-y-3">
              <ShieldCheck className="h-8 w-8 text-amber-400" />
              <h4 className="text-base font-bold text-white">Vendor Verification</h4>
              <p className="text-xs text-slate-400 leading-relaxed">
                Enables local suppliers and vendors to benchmark stock before stocking shelves.
              </p>
            </div>
          </div>
        </div>
      </section>

      {/* CTA Section */}
      <section className="py-20 bg-emerald-600 text-white text-center">
        <div className="mx-auto max-w-4xl px-4 sm:px-6 lg:px-8 space-y-6">
          <h2 className="text-3xl sm:text-4xl font-black">Ready to scan your first food item?</h2>
          <p className="text-emerald-100 text-base max-w-2xl mx-auto">
            Join thousands of health-conscious consumers and verified vendors ensuring food safety every day.
          </p>
          <div className="flex flex-col sm:flex-row items-center justify-center gap-4 pt-4">
            <Link
              href="/register"
              className="w-full sm:w-auto rounded-xl bg-slate-900 px-8 py-3.5 text-sm font-bold text-white shadow-lg hover:bg-slate-800 transition-all"
            >
              Create Free Account
            </Link>
            <Link
              href="/scan/packaged"
              className="w-full sm:w-auto rounded-xl bg-white px-8 py-3.5 text-sm font-bold text-emerald-900 shadow-lg hover:bg-emerald-50 transition-all"
            >
              Try Instant Scan
            </Link>
          </div>
        </div>
      </section>

      {/* Footer */}
      <footer className="border-t border-slate-200 bg-white py-8 text-center text-xs text-slate-500">
        <div className="mx-auto max-w-7xl px-4 flex flex-col sm:flex-row items-center justify-between gap-4">
          <p>© 2026 FraudShield AI. All rights reserved.</p>
          <div className="flex gap-6">
            <a href="#" className="hover:text-emerald-600">Privacy Policy</a>
            <a href="#" className="hover:text-emerald-600">Terms of Service</a>
            <a href="#" className="hover:text-emerald-600">Safety Standards</a>
          </div>
        </div>
      </footer>
    </div>
  );
}
