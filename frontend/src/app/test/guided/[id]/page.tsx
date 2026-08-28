'use client';

import { use, useEffect, useState } from 'react';
import { useRouter } from 'next/navigation';
import Link from 'next/link';
import { Navbar } from '@/components/Navbar';
import { Sidebar } from '@/components/Sidebar';
import { StepCard } from '@/components/StepCard';
import { UploadCard } from '@/components/UploadCard';
import { LoadingSpinner } from '@/components/LoadingSpinner';
import { apiRequest, fileToBase64 } from '@/lib/api';
import { KnowledgeResponse, ScanResponse } from '@/types';
import {
  Beaker,
  ArrowLeft,
  ArrowRight,
  CheckCircle2,
  Upload,
  AlertCircle,
} from 'lucide-react';

export default function GuidedTestPage({ params }: { params: Promise<{ id: string }> }) {
  const resolvedParams = use(params);
  const foodCategory = decodeURIComponent(resolvedParams.id || 'Milk');

  const router = useRouter();
  const [knowledge, setKnowledge] = useState<KnowledgeResponse | null>(null);
  const [currentStepIndex, setCurrentStepIndex] = useState(0);
  const [testResultImage, setTestResultImage] = useState<File | null>(null);
  const [userObservations, setUserObservations] = useState('');
  const [testPositive, setTestPositive] = useState<boolean>(false);
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    async function loadKnowledge() {
      try {
        const results = await apiRequest<KnowledgeResponse[]>(`/knowledge?query=${encodeURIComponent(foodCategory)}`);
        if (results && results.length > 0) {
          setKnowledge(results[0]);
        } else {
          // Fallback search
          const all = await apiRequest<KnowledgeResponse[]>('/knowledge');
          const found = all.find(k => k.name.toLowerCase().includes(foodCategory.toLowerCase()) || k.foodCategory.toLowerCase().includes(foodCategory.toLowerCase()));
          if (found) setKnowledge(found);
        }
      } catch (err) {
        // Ignored fallback
      }
    }
    loadKnowledge();
  }, [foodCategory]);

  const defaultSteps = [
    {
      stepNumber: 1,
      title: 'Prepare Sample',
      description: `Take a small 5ml sample of ${foodCategory} in a clear glass container.`,
      estimatedTime: '1 min',
    },
    {
      stepNumber: 2,
      title: 'Apply Home Test Method',
      description: knowledge?.homeTestMethod || `Perform standard purity/dissolution test for ${foodCategory}.`,
      tip: 'Observe carefully for precipitate, color bleeding, or abnormal separation layers.',
      estimatedTime: '3 mins',
    },
    {
      stepNumber: 3,
      title: 'Record & Photograph Result',
      description: 'Observe whether reaction is positive for adulterants and capture a clear photo of reaction.',
      estimatedTime: '1 min',
    },
  ];

  const totalSteps = defaultSteps.length;
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

  const handleAnalyzeResult = async () => {
    setIsSubmitting(true);
    setError(null);

    try {
      let imageUrl: string | undefined = undefined;
      if (testResultImage) {
        imageUrl = await fileToBase64(testResultImage);
      }

      const requestBody = {
        scanType: 'ADULTERATION',
        productName: `${foodCategory} Adulteration Test`,
        imageUrl,
        foodCategory,
        testType: 'Standard Household Chemical/Visual Test',
        userObservations: userObservations.trim() || 'Visual and reaction test completed.',
        testPositive: testPositive,
      };

      const scanResult = await apiRequest<ScanResponse>('/scans', {
        method: 'POST',
        body: JSON.stringify(requestBody),
      });

      router.push(`/report/packaged/${scanResult.id}`);
    } catch (err: any) {
      setError(err?.message || 'Failed to submit test result.');
    } finally {
      setIsSubmitting(false);
    }
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

          {error && (
            <div className="p-4 rounded-xl bg-rose-50 border border-rose-200 text-xs font-semibold text-rose-700 flex items-center gap-2">
              <AlertCircle className="h-4 w-4 shrink-0" />
              <span>{error}</span>
            </div>
          )}

          {/* Guide Header */}
          <div className="rounded-3xl border border-slate-200/80 bg-white p-6 sm:p-8 shadow-sm space-y-4">
            <div className="flex flex-col sm:flex-row sm:items-center justify-between gap-4">
              <div>
                <span className="rounded-full bg-emerald-100 px-3 py-1 text-xs font-bold text-emerald-800">
                  Guided Reaction Test Protocol
                </span>
                <h1 className="text-2xl sm:text-3xl font-black text-slate-900 mt-2">
                  Testing Adulteration: {foodCategory}
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

            {/* Target adulterants & procedure */}
            {knowledge && (
              <div className="pt-4 border-t border-slate-100 space-y-2">
                <h3 className="text-xs font-bold uppercase tracking-wider text-slate-500 flex items-center gap-2">
                  <Beaker className="h-4 w-4 text-emerald-600" />
                  Common Target Adulterants:
                </h3>
                <p className="text-xs text-slate-700 font-semibold bg-slate-50 p-3 rounded-xl border border-slate-200/60">
                  {knowledge.commonAdulterants}
                </p>
              </div>
            )}
          </div>

          {/* Current Step Display */}
          <div className="space-y-4">
            <h2 className="text-lg font-extrabold text-slate-900">Step Instructions</h2>
            <StepCard step={defaultSteps[currentStepIndex]} isActive={true} />
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

          {/* Result Upload & Observation Box */}
          <div className="rounded-3xl border border-emerald-200 bg-emerald-50/20 p-6 sm:p-8 space-y-4">
            <div className="space-y-1">
              <h3 className="text-lg font-bold text-slate-900 flex items-center gap-2">
                <Upload className="h-5 w-5 text-emerald-600" />
                Upload Reaction Result & Submit Observations
              </h3>
              <p className="text-xs text-slate-600">
                Take a clear photo of the test result and select whether an adulteration marker / color change was observed.
              </p>
            </div>

            <UploadCard
              title="Upload Reaction Photo"
              description="Click to select or drop reaction photo."
              onImageSelected={(file) => setTestResultImage(file)}
            />

            <div className="space-y-2">
              <label className="block text-xs font-bold uppercase tracking-wider text-slate-700">
                Observations / Notes
              </label>
              <input
                type="text"
                value={userObservations}
                onChange={(e) => setUserObservations(e.target.value)}
                placeholder="e.g., Color turned deep yellow / dark layer observed at bottom."
                className="w-full rounded-xl border border-slate-300 p-3 text-sm text-slate-900 focus:border-emerald-500 focus:outline-none"
              />
            </div>

            <div className="flex items-center gap-4 pt-2">
              <span className="text-xs font-bold text-slate-700">Adulteration Indicator Observed?</span>
              <label className="flex items-center gap-1.5 text-xs font-semibold cursor-pointer">
                <input
                  type="radio"
                  name="testPositive"
                  checked={testPositive === false}
                  onChange={() => setTestPositive(false)}
                  className="accent-emerald-600"
                />
                Negative (No Adulterant Found)
              </label>
              <label className="flex items-center gap-1.5 text-xs font-semibold cursor-pointer text-rose-700">
                <input
                  type="radio"
                  name="testPositive"
                  checked={testPositive === true}
                  onChange={() => setTestPositive(true)}
                  className="accent-rose-600"
                />
                Positive (Adulterant Flagged)
              </label>
            </div>

            <div className="flex justify-end pt-2">
              <button
                onClick={handleAnalyzeResult}
                disabled={isSubmitting}
                className="w-full sm:w-auto inline-flex items-center justify-center gap-2 rounded-xl bg-emerald-600 px-8 py-3.5 text-sm font-bold text-white shadow-md hover:bg-emerald-700 hover:shadow-emerald-600/20 transition-all disabled:opacity-50"
              >
                {isSubmitting ? (
                  <span>Evaluating Reaction Result...</span>
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
              <LoadingSpinner label="Evaluating risk level and submitting scan report..." />
            </div>
          )}
        </main>
      </div>
    </div>
  );
}
