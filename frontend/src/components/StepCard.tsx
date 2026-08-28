'use client';

import { GuidedTestStep } from '@/types';
import { Clock, Lightbulb } from 'lucide-react';

interface StepCardProps {
  step: GuidedTestStep;
  isActive?: boolean;
}

export function StepCard({ step, isActive = false }: StepCardProps) {
  return (
    <div
      className={`rounded-2xl border p-5 transition-all ${
        isActive
          ? 'border-emerald-500 bg-emerald-50/30 shadow-md ring-1 ring-emerald-500'
          : 'border-slate-200 bg-white shadow-xs hover:border-slate-300'
      }`}
    >
      <div className="flex items-start gap-4">
        <div
          className={`flex h-9 w-9 shrink-0 items-center justify-center rounded-xl text-sm font-extrabold ${
            isActive ? 'bg-emerald-600 text-white shadow-sm' : 'bg-slate-100 text-slate-700'
          }`}
        >
          {step.stepNumber}
        </div>

        <div className="flex-1 space-y-1.5">
          <div className="flex items-center justify-between gap-2">
            <h4 className="text-base font-bold text-slate-900">{step.title}</h4>
            {step.estimatedTime && (
              <span className="flex items-center gap-1 text-xs font-semibold text-slate-400">
                <Clock className="h-3.5 w-3.5" />
                {step.estimatedTime}
              </span>
            )}
          </div>

          <p className="text-sm text-slate-600 leading-relaxed">{step.description}</p>

          {step.tip && (
            <div className="mt-2 flex items-start gap-2 rounded-xl bg-amber-50 p-2.5 text-xs text-amber-800 border border-amber-200/60">
              <Lightbulb className="h-4 w-4 shrink-0 text-amber-600 mt-0.5" />
              <span>
                <strong>Pro Tip:</strong> {step.tip}
              </span>
            </div>
          )}
        </div>
      </div>
    </div>
  );
}
