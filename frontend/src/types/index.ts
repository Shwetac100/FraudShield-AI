export type UserRole = 'CONSUMER' | 'VENDOR';

export interface User {
  id: string;
  email: string;
  fullName: string;
  role: UserRole;
  businessName?: string;
}

export type ScanType = 'PACKAGED' | 'ADULTERATION';
export type RiskLevel = 'LOW' | 'MEDIUM' | 'HIGH' | 'CRITICAL';

export interface ScanHistoryItem {
  id: string;
  title: string;
  type: ScanType;
  date: string;
  status: 'Completed' | 'Pending' | 'Flagged';
  score?: number;
  riskLevel?: RiskLevel;
  thumbnailUrl?: string;
  summary: string;
}

export interface PackagedReportData {
  id: string;
  productName: string;
  brand: string;
  category: string;
  scanDate: string;
  qualityScore: number; // 0 - 100
  verdict: 'Healthy Choice' | 'Moderate Quality' | 'Poor Choice / Unhealthy';
  frontImage?: string;
  backImage?: string;
  ingredientAnalysis: {
    totalIngredients: number;
    naturalCount: number;
    processedCount: number;
    additiveCount: number;
    ingredientsList: string[];
  };
  nutritionSummary: {
    calories: string;
    sugars: string;
    sodium: string;
    transFat: string;
    protein: string;
    healthRating: 'Good' | 'Moderate' | 'High Risk';
  };
  harmfulAdditives: {
    name: string;
    eNumber?: string;
    severity: 'LOW' | 'MEDIUM' | 'HIGH';
    description: string;
  }[];
  healthierAlternatives: {
    id: string;
    name: string;
    brand: string;
    score: number;
    reason: string;
  }[];
  explainableReasoning: {
    summary: string;
    positives: string[];
    concerns: string[];
    scientificBasis: string;
  };
}

export interface GuidedTestStep {
  stepNumber: number;
  title: string;
  description: string;
  tip?: string;
  estimatedTime?: string;
}

export interface AdulterationTestGuide {
  id: string;
  foodName: string;
  commonAdulterants: string[];
  materialsRequired: string[];
  steps: GuidedTestStep[];
}

export interface AdulterationReportData {
  id: string;
  foodName: string;
  category: string;
  testDate: string;
  riskLevel: RiskLevel;
  confidenceScore: number; // 0 - 100 %
  detectedAdulterants: string[];
  aiObservations: {
    visualMarker: string;
    chemicalIndicator: string;
    textureNote: string;
  };
  recommendedNextAction: string;
  disclaimer: string;
  testImage?: string;
  resultImage?: string;
}
