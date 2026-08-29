'use client';

import { useState } from 'react';
import { useRouter } from 'next/navigation';
import { Navbar } from '@/components/Navbar';
import { Sidebar } from '@/components/Sidebar';
import { UploadCard } from '@/components/UploadCard';
import { LoadingSpinner } from '@/components/LoadingSpinner';
import { apiRequest, fileToBase64 } from '@/lib/api';
import { ScanResponse } from '@/types';
import { ScanLine, Sparkles, CheckCircle2, ArrowRight, AlertCircle } from 'lucide-react';

export default function PackagedScanPage() {
  const router = useRouter();
  const [productName, setProductName] = useState('');
  const [rawText, setRawText] = useState('');
  const [frontImage, setFrontImage] = useState<File | null>(null);
  const [backImage, setBackImage] = useState<File | null>(null);
  const [isAnalyzing, setIsAnalyzing] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const handleAnalyze = async () => {
    if (!frontImage && !backImage && !rawText.trim()) {
      setError('Please provide at least one photo or paste ingredients text.');
      return;
    }

    setIsAnalyzing(true);
    setError(null);

    try {
      let imageUrl: string | undefined = undefined;
      if (backImage) {
        imageUrl = await fileToBase64(backImage);
      } else if (frontImage) {
        imageUrl = await fileToBase64(frontImage);
      }

      const requestBody = {
        scanType: 'PACKAGED',
        productName: productName.trim() || undefined,
        imageUrl: imageUrl,
        rawIngredientsText: rawText.trim() || undefined,
      };

      const result = await apiRequest<ScanResponse>('/scans', {
        method: 'POST',
        body: JSON.stringify(requestBody),
      });

      router.push(`/report/packaged/${result.id}`);
    } catch (err: any) {
      setError(err?.message || 'Failed to submit scan to backend. Please check authentication or inputs.');
    } finally {
      setIsAnalyzing(false);
    }
  };

  return (
    <div className="min-h-screen bg-slate-50 flex flex-col font-sans">
      <Navbar />

      <div className="flex-1 flex max-w-7xl w-full mx-auto">
        <Sidebar />

        <main className="flex-1 p-4 sm:p-6 lg:p-8 space-y-8 overflow-y-auto">
          {/* Header */}
          <div className="space-y-2">
            <div className="inline-flex items-center gap-2 rounded-full bg-sky-100 px-3 py-1 text-xs font-bold text-sky-700">
              <ScanLine className="h-4 w-4" />
              <span>Packaged Product Scan</span>
            </div>
            <h1 className="text-2xl sm:text-3xl font-black text-slate-900">
              Analyze Packaged Food Label
            </h1>
            <p className="text-sm text-slate-600 max-w-2xl">
              Upload front and back photos of any packaged product or paste the ingredients list directly.
            </p>
          </div>

          {error && (
            <div className="flex items-center gap-2 p-4 rounded-xl bg-rose-50 border border-rose-200 text-xs font-semibold text-rose-700">
              <AlertCircle className="h-4 w-4 shrink-0" />
              <span>{error}</span>
            </div>
          )}

          <div className="space-y-2">
            <label className="block text-xs font-bold uppercase tracking-wider text-slate-500">
              Product Name (Optional)
            </label>
            <input
              type="text"
              value={productName}
              onChange={(e) => setProductName(e.target.value)}
              placeholder="e.g. NaturaBlend Almond Milk or Potato Chips"
              className="w-full rounded-xl border border-slate-300 px-4 py-2.5 text-sm text-slate-900 focus:border-emerald-500 focus:outline-none"
            />
          </div>

          {/* Upload Cards Grid */}
          <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
            <div className="space-y-2">
              <span className="text-xs font-bold uppercase tracking-wider text-slate-500">
                Step 1: Front Packaging
              </span>
              <UploadCard
                title="Front Label / Branding Image"
                description="Upload a photo displaying product name, brand logo, and weight."
                onImageSelected={(file) => setFrontImage(file)}
              />
            </div>

            <div className="space-y-2">
              <span className="text-xs font-bold uppercase tracking-wider text-slate-500">
                Step 2: Back Packaging
              </span>
              <UploadCard
                title="Back Label / Ingredients List"
                description="Upload a clear photo showing ingredients, nutrition panel, and batch details."
                onImageSelected={(file) => setBackImage(file)}
              />
            </div>
          </div>

          <div className="space-y-2">
            <label className="block text-xs font-bold uppercase tracking-wider text-slate-500">
              Or Paste Ingredients / OCR Raw Text
            </label>
            <textarea
              rows={3}
              value={rawText}
              onChange={(e) => setRawText(e.target.value)}
              placeholder="e.g., Water, Almonds, Salt, Gellan Gum, E418, E621, Palm Oil..."
              className="w-full rounded-xl border border-slate-300 p-3 text-sm text-slate-900 focus:border-emerald-500 focus:outline-none"
            />
          </div>

          {/* Guidelines Banner */}
          <div className="rounded-2xl border border-slate-200 bg-white p-5 space-y-3">
            <h3 className="text-sm font-bold text-slate-900 flex items-center gap-2">
              <Sparkles className="h-4 w-4 text-emerald-600" />
              For best OCR detection accuracy:
            </h3>
            <ul className="grid grid-cols-1 sm:grid-cols-3 gap-3 text-xs text-slate-600">
              <li className="flex items-start gap-2">
                <CheckCircle2 className="h-4 w-4 text-emerald-500 shrink-0 mt-0.5" />
                <span>Ensure good lighting with no glares on shiny plastic wrapping.</span>
              </li>
              <li className="flex items-start gap-2">
                <CheckCircle2 className="h-4 w-4 text-emerald-500 shrink-0 mt-0.5" />
                <span>Keep text flat without deep wrinkles or folds in packaging.</span>
              </li>
              <li className="flex items-start gap-2">
                <CheckCircle2 className="h-4 w-4 text-emerald-500 shrink-0 mt-0.5" />
                <span>Ensure ingredients list and E-numbers are completely in frame.</span>
              </li>
            </ul>
          </div>

          {/* Action Area */}
          <div className="flex flex-col sm:flex-row items-center justify-between gap-4 border-t border-slate-200 pt-6">
            <p className="text-xs text-slate-500">
              {!frontImage && !backImage && !rawText.trim()
                ? 'Please upload at least one label image or enter text to enable analysis.'
                : 'Ready to submit for AI breakdown.'}
            </p>

            <button
              onClick={handleAnalyze}
              disabled={(!frontImage && !backImage && !rawText.trim()) || isAnalyzing}
              className={`w-full sm:w-auto inline-flex items-center justify-center gap-2 rounded-xl px-8 py-3.5 text-sm font-bold shadow-md transition-all ${
                (!frontImage && !backImage && !rawText.trim()) || isAnalyzing
                  ? 'bg-slate-200 text-slate-400 cursor-not-allowed shadow-none'
                  : 'bg-emerald-600 text-white hover:bg-emerald-700 hover:shadow-emerald-600/20'
              }`}
            >
              {isAnalyzing ? (
                <span>Analyzing Product...</span>
              ) : (
                <>
                  <span>Analyze Product</span>
                  <ArrowRight className="h-4 w-4" />
                </>
              )}
            </button>
          </div>

          {/* Loading Overlay State */}
          {isAnalyzing && (
            <div className="rounded-2xl border border-emerald-200 bg-emerald-50/50 p-6">
              <LoadingSpinner label="Extracting ingredient parameters & calculating quality score..." />
            </div>
          )}
        </main>
      </div>
    </div>
  );
}
