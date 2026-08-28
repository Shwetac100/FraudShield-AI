'use client';

import Link from 'next/link';
import { usePathname } from 'next/navigation';
import { LayoutDashboard, ScanLine, FlaskConical, History, User, Settings, ShieldAlert } from 'lucide-react';

export function Sidebar() {
  const pathname = usePathname();

  const navItems = [
    { name: 'Dashboard', href: '/dashboard', icon: LayoutDashboard },
    { name: 'Packaged Scan', href: '/scan/packaged', icon: ScanLine },
    { name: 'Adulteration Test', href: '/scan/testable', icon: FlaskConical },
    { name: 'Scan History', href: '/dashboard#history', icon: History },
  ];

  return (
    <aside className="w-64 shrink-0 hidden lg:block border-r border-slate-200/80 bg-white min-h-[calc(100vh-4rem)] p-4">
      <div className="space-y-6">
        {/* Navigation Section */}
        <div>
          <p className="px-3 text-xs font-semibold uppercase tracking-wider text-slate-400 mb-2">
            Main Navigation
          </p>
          <nav className="space-y-1">
            {navItems.map((item) => {
              const Icon = item.icon;
              const isActive = pathname === item.href;
              return (
                <Link
                  key={item.name}
                  href={item.href}
                  className={`flex items-center gap-3 rounded-xl px-3 py-2.5 text-sm font-semibold transition-all ${
                    isActive
                      ? 'bg-emerald-50 text-emerald-700 shadow-sm'
                      : 'text-slate-600 hover:bg-slate-50 hover:text-slate-900'
                  }`}
                >
                  <Icon className={`h-5 w-5 ${isActive ? 'text-emerald-600' : 'text-slate-400'}`} />
                  {item.name}
                </Link>
              );
            })}
          </nav>
        </div>

        {/* Account & Settings */}
        <div>
          <p className="px-3 text-xs font-semibold uppercase tracking-wider text-slate-400 mb-2">
            Account & System
          </p>
          <div className="space-y-1">
            <Link
              href="/login"
              className="flex items-center gap-3 rounded-xl px-3 py-2.5 text-sm font-semibold text-slate-600 hover:bg-slate-50 hover:text-slate-900 transition-all"
            >
              <User className="h-5 w-5 text-slate-400" />
              User Profile
            </Link>
            <div className="flex items-center gap-3 rounded-xl px-3 py-2.5 text-sm font-semibold text-slate-400 cursor-not-allowed">
              <Settings className="h-5 w-5 text-slate-300" />
              Settings
            </div>
          </div>
        </div>

        {/* Quick Safety Tip Box */}
        <div className="rounded-2xl bg-gradient-to-br from-emerald-500 to-teal-700 p-4 text-white shadow-md">
          <div className="flex items-center gap-2 mb-2">
            <ShieldAlert className="h-5 w-5 text-emerald-200" />
            <h4 className="text-sm font-bold">Food Safety Tip</h4>
          </div>
          <p className="text-xs text-emerald-50 leading-relaxed">
            Always inspect sealed caps and labels on dairy products for tampered holograms before home testing.
          </p>
        </div>
      </div>
    </aside>
  );
}
