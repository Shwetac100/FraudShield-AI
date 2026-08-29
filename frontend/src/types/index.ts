export type UserRole = 'CONSUMER' | 'VENDOR' | 'ADMIN';

export interface User {
  id: string | number;
  email: string;
  fullName: string;
  role: UserRole;
  businessName?: string;
}

export type ScanType = 'PACKAGED' | 'ADULTERATION';
export type RiskLevel = 'LOW' | 'MEDIUM' | 'HIGH' | 'CRITICAL';

export interface ApiResponse<T> {
  success: boolean;
  message: string;
  data: T;
  timestamp?: string;
}

export interface AuthResponse {
  token: string;
  id: number;
  email: string;
  fullName: string;
  role: UserRole;
}

export interface VendorProfileDto {
  id: number;
  businessName?: string;
  businessAddress?: string;
  businessLicenseNumber?: string;
  qualityRating?: number;
  totalScans?: number;
  passedScans?: number;
}

export interface UserProfileResponse {
  id: number;
  email: string;
  fullName: string;
  role: UserRole;
  createdAt: string;
  vendorProfile?: VendorProfileDto;
}

export interface PackagedScanDetailsDto {
  rawText?: string;
  ingredientsText?: string;
  nutritionalInfo?: string;
  detectedENumbers?: string;
  detectedHarmfulAdditives?: string;
}

export interface AdulterationScanDetailsDto {
  foodCategory?: string;
  testType?: string;
  userObservations?: string;
  suspectedAdulterant?: string;
  testPositive?: boolean;
}

export interface ScanResponse {
  id: number;
  userId: number;
  scanType: ScanType;
  productName: string;
  riskLevel: RiskLevel;
  riskExplanation: string;
  summaryResult: string;
  imageUrl?: string;
  createdAt: string;
  packagedDetails?: PackagedScanDetailsDto;
  adulterationDetails?: AdulterationScanDetailsDto;
}

export interface KnowledgeResponse {
  id: number;
  name: string;
  foodCategory: string;
  description: string;
  commonAdulterants: string;
  homeTestMethod: string;
  healthImpacts: string;
  defaultSeverity: RiskLevel;
  regulatoryLimits: string;
  createdAt: string;
}

export interface VendorDashboardSummary {
  businessName?: string;
  businessAddress?: string;
  businessLicenseNumber?: string;
  qualityRating?: number;
  totalScans?: number;
  passedScans?: number;
  flaggedScans?: number;
  compliancePercentage?: number;
  recentScans: ScanResponse[];
}

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
  qualityScore: number;
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
  confidenceScore: number;
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
