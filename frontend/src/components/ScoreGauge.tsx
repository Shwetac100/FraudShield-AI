'use client';

interface ScoreGaugeProps {
  score: number; // 0 to 100
  size?: 'sm' | 'md' | 'lg';
  label?: string;
}

export function ScoreGauge({ score, size = 'md', label = 'Quality Score' }: ScoreGaugeProps) {
  // Determine color based on score
  let color = 'text-emerald-500 stroke-emerald-500';
  let badgeText = 'Excellent';
  let badgeBg = 'bg-emerald-100 text-emerald-800';

  if (score < 50) {
    color = 'text-red-500 stroke-red-500';
    badgeText = 'Poor Quality';
    badgeBg = 'bg-red-100 text-red-800';
  } else if (score < 75) {
    color = 'text-amber-500 stroke-amber-500';
    badgeText = 'Moderate';
    badgeBg = 'bg-amber-100 text-amber-800';
  }

  const strokeWidth = size === 'sm' ? 8 : size === 'lg' ? 12 : 10;
  const radius = 42;
  const circumference = 2 * Math.PI * radius;
  const strokeDashoffset = circumference - (score / 100) * circumference;

  const sizeClasses = {
    sm: 'w-28 h-28',
    md: 'w-40 h-40',
    lg: 'w-52 h-52',
  };

  return (
    <div className="flex flex-col items-center justify-center">
      <div className={`relative flex items-center justify-center ${sizeClasses[size]}`}>
        <svg className="w-full h-full transform -rotate-90" viewBox="0 0 100 100">
          {/* Background circle */}
          <circle
            cx="50"
            cy="50"
            r={radius}
            className="stroke-slate-100"
            strokeWidth={strokeWidth}
            fill="transparent"
          />
          {/* Progress gauge circle */}
          <circle
            cx="50"
            cy="50"
            r={radius}
            className={`transition-all duration-1000 ease-out ${color}`}
            strokeWidth={strokeWidth}
            strokeDasharray={circumference}
            strokeDashoffset={strokeDashoffset}
            strokeLinecap="round"
            fill="transparent"
          />
        </svg>

        {/* Center text display */}
        <div className="absolute flex flex-col items-center justify-center text-center">
          <span className="text-3xl font-extrabold tracking-tight text-slate-900">{score}</span>
          <span className="text-[11px] font-semibold text-slate-400 uppercase tracking-wide">/100</span>
        </div>
      </div>

      <div className="mt-2 text-center">
        <span className={`inline-block rounded-full px-2.5 py-0.5 text-xs font-bold ${badgeBg}`}>
          {badgeText}
        </span>
        {label && <p className="mt-1 text-xs font-medium text-slate-500">{label}</p>}
      </div>
    </div>
  );
}
