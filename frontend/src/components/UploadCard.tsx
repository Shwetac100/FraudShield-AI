'use client';

import { useState, useRef } from 'react';
import { UploadCloud, Image as ImageIcon, CheckCircle2, X } from 'lucide-react';

interface UploadCardProps {
  title: string;
  description: string;
  onImageSelected: (file: File | null) => void;
  acceptedTypes?: string;
}

export function UploadCard({
  title,
  description,
  onImageSelected,
  acceptedTypes = 'image/png, image/jpeg, image/webp',
}: UploadCardProps) {
  const [preview, setPreview] = useState<string | null>(null);
  const [fileName, setFileName] = useState<string | null>(null);
  const fileInputRef = useRef<HTMLInputElement>(null);

  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0];
    if (file) {
      setFileName(file.name);
      const url = URL.createObjectURL(file);
      setPreview(url);
      onImageSelected(file);
    }
  };

  const handleRemove = (e: React.MouseEvent) => {
    e.stopPropagation();
    setPreview(null);
    setFileName(null);
    if (fileInputRef.current) {
      fileInputRef.current.value = '';
    }
    onImageSelected(null);
  };

  return (
    <div
      onClick={() => fileInputRef.current?.click()}
      className={`group relative flex flex-col items-center justify-center rounded-2xl border-2 border-dashed p-6 text-center cursor-pointer transition-all ${
        preview
          ? 'border-emerald-500 bg-emerald-50/20 shadow-sm'
          : 'border-slate-300 hover:border-emerald-500 hover:bg-slate-50/80 shadow-xs'
      }`}
    >
      <input
        type="file"
        ref={fileInputRef}
        accept={acceptedTypes}
        onChange={handleFileChange}
        className="hidden"
      />

      {preview ? (
        <div className="relative w-full space-y-3">
          <div className="relative mx-auto h-44 w-full max-w-xs overflow-hidden rounded-xl border border-slate-200 bg-slate-900 shadow-sm">
            {/* eslint-disable-next-line @next/next/no-img-element */}
            <img src={preview} alt="Upload preview" className="h-full w-full object-contain" />
            <button
              onClick={handleRemove}
              type="button"
              className="absolute top-2 right-2 rounded-full bg-slate-900/80 p-1.5 text-white hover:bg-red-600 transition-colors"
            >
              <X className="h-4 w-4" />
            </button>
          </div>
          <div className="flex items-center justify-center gap-1.5 text-sm font-semibold text-emerald-700">
            <CheckCircle2 className="h-4 w-4 text-emerald-600" />
            <span className="truncate max-w-[200px]">{fileName}</span>
          </div>
          <p className="text-xs text-slate-500">Click or drag to replace image</p>
        </div>
      ) : (
        <div className="space-y-3 py-4">
          <div className="mx-auto flex h-14 w-14 items-center justify-center rounded-2xl bg-emerald-100 text-emerald-600 group-hover:scale-110 transition-transform">
            <UploadCloud className="h-7 w-7" />
          </div>
          <div className="space-y-1">
            <h3 className="text-base font-bold text-slate-900">{title}</h3>
            <p className="text-xs text-slate-500 max-w-xs mx-auto">{description}</p>
          </div>
          <div className="inline-flex items-center gap-1.5 rounded-lg border border-slate-200 bg-white px-3 py-1.5 text-xs font-semibold text-slate-700 shadow-xs group-hover:border-emerald-500 group-hover:text-emerald-700">
            <ImageIcon className="h-3.5 w-3.5 text-slate-400" />
            Browse File
          </div>
        </div>
      )}
    </div>
  );
}
