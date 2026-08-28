'use client';

import { useState } from 'react';
import { useRouter } from 'next/navigation';
import { Navbar } from '@/components/Navbar';
import { Sidebar } from '@/components/Sidebar';
import { UploadCard } from '@/components/UploadCard';
import { FlaskConical, ArrowRight, ShieldAlert, Check } from 'lucide-react';

export default function TestableScanPage() {
  const router = useRouter();
  const [selectedFood, setSelectedFood] = useState<string>('Milk');
  const [foodImage, setFoodImage] = useState<File | null>(null);

  const testableItems = [
    {
      id: 'Milk',
      name: 'Fresh Milk / Dairy',
      adulterants: ['Water', 'Detergent', 'Urea', 'Starch'],
      icon: '🥛',
    },
    {
      id: 'Honey',
      name: 'Pure Honey',
      adulterants: ['Sugar Syrup', 'High Fructose Syrup', 'Water'],
      icon: '🍯',
    },
    {
      id: 'Turmeric',
      name: 'Turmeric Powder',
      adulterants: ['Metanil Yellow', 'Lead Chromate', 'Chalk Powder'],
      icon: '🟡',
    },
    {
      id: 'Chili',
      name: 'Chili Powder',
      adulterants: ['Brick Powder', 'Rhodamine B', 'Sawdust'],
      icon: '🌶️',
    },
  ];

  const handleContinue = () => {
    router.push(`/test/guided/${encodeURIComponent(selectedFood)}`);
  };

  return (
    <div className="min-h-screen bg-slate-50 flex flex-col font-sans">
      <Navbar />

      <div className="flex-1 flex max-w-7xl w-full mx-auto">
        <Sidebar />

        <main className="flex-1 p-4 sm:p-6 lg:p-8 space-y-8 overflow-y-auto">
          {/* Header */}
          <div className="space-y-2">
            <div className="inline-flex items-center gap-2 rounded-full bg-emerald-100 px-3 py-1 text-xs font-bold text-emerald-800">
              <FlaskConical className="h-4 w-4" />
              <span>Adulteration Screening</span>
            </div>
            <h1 className="text-2xl sm:text-3xl font-black text-slate-900">
              Test Raw Food for Adulteration
            </h1>
            <p className="text-sm text-slate-600 max-w-2xl">
              Select the food category you wish to test. We provide a step-by-step guided chemical reaction protocol to verify purity.
            </p>
          </div>

          {/* Select Staple Food */}
          <div className="space-y-3">
            <label className="block text-xs font-bold uppercase tracking-wider text-slate-500">
              Select Food Category
            </label>
            <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-4">
              {testableItems.map((item) => {
                const isSelected = selectedFood === item.id;
                return (
                  <div
                    key={item.id}
                    onClick={() => setSelectedFood(item.id)}
                    className={`relative cursor-pointer p-5 rounded-2xl border-2 transition-all space-y-3 ${
                      isSelected
                        ? 'border-emerald-600 bg-emerald-50/40 shadow-sm'
                        : 'border-slate-200 bg-white hover:border-slate-300'
                    }`}
                  >
                    <div className="flex items-center justify-between">
                      <span className="text-3xl">{item.icon}</span>
                      {isSelected && (
                        <div className="flex h-6 w-6 items-center justify-center rounded-full bg-emerald-600 text-white">
                          <Check className="h-3.5 w-3.5" />
                        </div>
                      )}
                    </div>
                    <div>
                      <h3 className="text-base font-bold text-slate-900">{item.name}</h3>
                      <p className="text-xs text-slate-500 mt-1">
                        Targets: {item.adulterants.join(', ')}
                      </p>
                    </div>
                  </div>
                );
              })}
            </div>
          </div>

          {/* Optional Raw Food Image Upload */}
          <div className="space-y-2">
            <label className="block text-xs font-bold uppercase tracking-wider text-slate-500">
              Upload Raw Sample Photo (Optional)
            </label>
            <UploadCard
              title="Upload Sample Photo"
              description="Snap a picture of the raw sample in its original container before starting the test."
              onImageSelected={(file) => setFoodImage(file)}
            />
          </div>

          {/* Action area */}
          <div className="flex flex-col sm:flex-row items-center justify-between gap-4 border-t border-slate-200 pt-6">
            <div className="flex items-center gap-2 text-xs text-slate-500">
              <ShieldAlert className="h-4 w-4 text-emerald-600" />
              <span>Next step will show required test steps and allow submitting test observations.</span>
            </div>

            <button
              onClick={handleContinue}
              className="w-full sm:w-auto inline-flex items-center justify-center gap-2 rounded-xl bg-emerald-600 px-8 py-3.5 text-sm font-bold text-white shadow-md hover:bg-emerald-700 hover:shadow-emerald-600/20 transition-all"
            >
              <span>Continue to Guided Test</span>
              <ArrowRight className="h-4 w-4" />
            </button>
          </div>
        </main>
      </div>
    </div>
  );
}
