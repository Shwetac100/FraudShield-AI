'use client';

import { use, useState } from 'react';
import { useRouter } from 'next/navigation';
import Link from 'next/link';
import { Navbar } from '@/components/Navbar';
import { Sidebar } from '@/components/Sidebar';
import { StepCard } from '@/components/StepCard';
import { UploadCard } from '@/components/UploadCard';
import { LoadingSpinner } from '@/components/LoadingSpinner';
import { mockAdulterationGuides } from '@/lib/mockData';
import {
  FlaskConical,
  Beaker,
  ArrowLeft,
  ArrowRight,
  CheckCircle2,
  Upload,
} from 'lucide-react';

export default function GuidedTestPage({ params }: { params: Promise<{ id: string }> }) {
  const resolvedParams = use(params);
  const guideId = resolvedParams.id || 'ghee';
  const guide = mockAdulterationGuides[guideId] || mockAdulterationGuides['ghee'];

  const router = useRouter();
  const [currentStepIndex, setCurrentStepIndex] = useState(0);
  const [testResultImage, setTestResultImage] = useState<File | null>(null);
  const [isSubmitting, setIsSubmitting] = useState(false);

  const totalSteps = guide.steps.length;
  const progressPercent = Math.round(((currentStepIndex + 1) / totalSteps) * 100);

  const handleNextStep = () => {
    if (currentStepIndex < totalSteps - 1) {
      setCurrentStepIndex(currentStepIndex + 1);
    }
  };

  const handlePrevStep = () => {
    if (currentStepIndex > 0) {
      setCurrentStepIndex(currentStepIndex - 1);
    }
  };

  const handleAnalyzeResult = () => {
    setIsSubmitting(true);
    setTimeout(() => {
      setIsSubmitting(false);
      router.push('/report/adulteration/scan-102');
    }, 1800);
  };

  return (
    <div className="min-h-screen bg-slate-50 flex flex-col font-sans">
      <Navbar />

      <div className="flex-1 flex max-w-7xl w-full mx-auto">
        <Sidebar />

        <main className="flex-1 p-4 sm:p-6 lg:p-8 space-y-8 overflow-y-auto">
          {/* Top navigation */}
          <Link
            href="/scan/testable"
            className="inline-flex items-center gap-1.5 text-xs font-bold text-slate-500 hover:text-slate-800 transition-colors"
          >
            <ArrowLeft className="h-4 w-4" />
            Select Different Food
          </Link>

          {/* Guide Header */}
          <div className="rounded-3xl border border-slate-200/80 bg-white p-6 sm:p-8 shadow-sm space-y-4">
            <div className="flex flex-col sm:flex-row sm:items-center justify-between gap-4">
              <div>
                <span className="rounded-full bg-emerald-100 px-3 py-1 text-xs font-bold text-emerald-800">
                  Guided Reaction Test Protocol
                </span>
                <h1 className="text-2xl sm:text-3xl font-black text-slate-900 mt-2">
                  Testing Adulteration: {guide.foodName}
                </h1>
              </div>

              {/* Progress Indicator */}
              <div className="shrink-0 space-y-1 text-right">
                <span className="text-xs font-bold text-slate-500">
                  Step {currentStepIndex + 1} of {totalSteps}
                </span>
                <div className="w-36 h-2.5 rounded-full bg-slate-100 overflow-hidden">
                  <div
                    className="h-full bg-emerald-600 transition-all duration-300"
                    style={{ width: `${progressPercent}%` }}
                  />
                </div>
              </div>
            </div>

            {/* Materials Required checklist */}
            <div className="pt-4 border-t border-slate-100 space-y-2">
              <h3 className="text-xs font-bold uppercase tracking-wider text-slate-500 flex items-center gap-2">
                <Beaker className="h-4 w-4 text-emerald-600" />
                Materials Required Before Starting:
              </h3>
              <ul className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-2 text-xs text-slate-700">
                {guide.materialsRequired.map((mat, i) => (
                  <li key={i} className="flex items-center gap-2 bg-slate-50 p-2 rounded-lg border border-slate-200/60">
                    <CheckCircle2 className="h-3.5 w-3.5 text-emerald-600 shrink-0" />
                    <span>{mat}</span>
                  </li>
                ))}
              </ul>
            </div>
          </div>

          {/* Current Step Display */}
          <div className="space-y-4">
            <h2 className="text-lg font-extrabold text-slate-900">Step Instructions</h2>
            <StepCard step={guide.steps[currentStepIndex]} isActive={true} />
          </div>

          {/* Step Navigation Controls */}
          <div className="flex items-center justify-between">
            <button
              onClick={handlePrevStep}
              disabled={currentStepIndex === 0}
              className={`inline-flex items-center gap-1.5 px-4 py-2 rounded-xl text-xs font-bold ${
                currentStepIndex === 0
                  ? 'text-slate-300 cursor-not-allowed'
                  : 'text-slate-700 bg-white border border-slate-200 hover:bg-slate-50'
              }`}
            >
              <ArrowLeft className="h-4 w-4" />
              Previous Step
            </button>

            <button
              onClick={handleNextStep}
              disabled={currentStepIndex === totalSteps - 1}
              className={`inline-flex items-center gap-1.5 px-4 py-2 rounded-xl text-xs font-bold ${
                currentStepIndex === totalSteps - 1
                  ? 'text-slate-300 cursor-not-allowed'
                  : 'bg-slate-900 text-white hover:bg-slate-800'
              }`}
            >
              Next Step
              <ArrowRight className="h-4 w-4" />
            </button>
          </div>

          {/* Result Upload Box (Shown at final step or accessible throughout) */}
          <div className="rounded-3xl border border-emerald-200 bg-emerald-50/20 p-6 sm:p-8 space-y-4">
            <div className="space-y-1">
              <h3 className="text-lg font-bold text-slate-900 flex items-center gap-2">
                <Upload className="h-5 w-5 text-emerald-600" />
                Upload Final Reaction Result
              </h3>
              <p className="text-xs text-slate-600">
                Take a clear, bright photo of the final liquid layer separation or color change in the glass tube.
              </p>
            </div>

            <UploadCard
              title="Upload Reaction Photo"
              description="Click to select or drop reaction photo for AI vision verification."
              onImageSelected={(file) => setTestResultImage(file)}
            />

            <div className="flex justify-end pt-2">
              <button
                onClick={handleAnalyzeResult}
                disabled={!testResultImage || isSubmitting}
                className={`w-full sm:w-auto inline-flex items-center justify-center gap-2 rounded-xl px-8 py-3.5 text-sm font-bold shadow-md transition-all ${
                  !testResultImage || isSubmitting
                    ? 'bg-slate-200 text-slate-400 cursor-not-allowed shadow-none'
                    : 'bg-emerald-600 text-white hover:bg-emerald-700 hover:shadow-emerald-600/20'
                }`}
              >
                {isSubmitting ? (
                  <span>Evaluating Chemical Spectrum...</span>
                ) : (
                  <>
                    <span>Generate Adulteration Report</span>
                    <ArrowRight className="h-4 w-4" />
                  </>
                )}
              </button>
            </div>
          </div>

          {/* Loading state indicator */}
          {isSubmitting && (
            <div className="rounded-2xl border border-emerald-200 bg-emerald-50/50 p-6">
              <LoadingSpinner label="Running computer vision analysis on test color spectrum..." />
            </div>
          )}
        </main>
      </div>
    </div>
  );
}
