'use client';

import Link from 'next/link';
import { ScanHistoryItem } from '@/types';
import { RiskBadge } from './RiskBadge';
import { ScanLine, FlaskConical, ArrowRight, Calendar } from 'lucide-react';

interface ScanCardProps {
  item: ScanHistoryItem;
}

export function ScanCard({ item }: ScanCardProps) {
  const isPackaged = item.type === 'PACKAGED';
  const targetUrl = `/report/packaged/${item.id}`;

  return (
    <div className="group rounded-2xl border border-slate-200/80 bg-white p-5 shadow-xs transition-all hover:border-emerald-300 hover:shadow-md">
      <div className="flex items-start justify-between gap-4">
        <div className="flex items-center gap-3">
          <div
            className={`flex h-11 w-11 shrink-0 items-center justify-center rounded-xl ${
              isPackaged ? 'bg-sky-100 text-sky-600' : 'bg-emerald-100 text-emerald-600'
            }`}
          >
            {isPackaged ? <ScanLine className="h-5 w-5" /> : <FlaskConical className="h-5 w-5" />}
          </div>
          <div>
            <span className="inline-block text-[10px] font-bold uppercase tracking-wider text-slate-400">
              {isPackaged ? 'Packaged Scan' : 'Adulteration Test'}
            </span>
            <h3 className="text-base font-bold text-slate-900 group-hover:text-emerald-600 transition-colors">
              {item.title}
            </h3>
          </div>
        </div>

        {item.riskLevel ? (
          <RiskBadge level={item.riskLevel} />
        ) : item.score !== undefined ? (
          <span className="rounded-lg bg-slate-100 px-2.5 py-1 text-xs font-bold text-slate-700">
            Score: {item.score}/100
          </span>
        ) : null}
      </div>

      <p className="mt-3 text-xs text-slate-600 line-clamp-2 leading-relaxed">{item.summary}</p>

      <div className="mt-4 flex items-center justify-between border-t border-slate-100 pt-3 text-xs text-slate-500">
        <div className="flex items-center gap-1.5">
          <Calendar className="h-3.5 w-3.5 text-slate-400" />
          <span>{item.date}</span>
        </div>

        <Link
          href={targetUrl}
          className="inline-flex items-center gap-1 font-semibold text-emerald-600 hover:text-emerald-700 group-hover:translate-x-0.5 transition-transform"
        >
          View Full Report
          <ArrowRight className="h-3.5 w-3.5" />
        </Link>
      </div>
    </div>
  );
}
