'use client';

import { Loader2 } from 'lucide-react';

interface LoadingSpinnerProps {
  label?: string;
}

export function LoadingSpinner({ label = 'Analyzing scanning results...' }: LoadingSpinnerProps) {
  return (
    <div className="flex flex-col items-center justify-center p-8 space-y-3">
      <Loader2 className="h-10 w-10 animate-spin text-emerald-600" />
      <p className="text-sm font-semibold text-slate-700 animate-pulse">{label}</p>
    </div>
  );
}
