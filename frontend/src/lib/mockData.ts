import { PackagedReportData, AdulterationReportData, AdulterationTestGuide, ScanHistoryItem } from '@/types';

export const mockScanHistory: ScanHistoryItem[] = [
  {
    id: 'scan-101',
    title: 'Organic Almond Milk',
    type: 'PACKAGED',
    date: '2026-08-28',
    status: 'Completed',
    score: 88,
    riskLevel: 'LOW',
    summary: 'High nutritional value with minimal added preservatives.',
  },
  {
    id: 'scan-102',
    title: 'Pure Desi Ghee Sample A',
    type: 'ADULTERATION',
    date: '2026-08-27',
    status: 'Flagged',
    riskLevel: 'HIGH',
    summary: 'Possible adulteration detected (Vanaspati / Starch content).',
  },
  {
    id: 'scan-103',
    title: 'Crispy Potato Chips',
    type: 'PACKAGED',
    date: '2026-08-25',
    status: 'Completed',
    score: 42,
    riskLevel: 'HIGH',
    summary: 'Excessive sodium and synthetic flavor enhancers (E621).',
  },
  {
    id: 'scan-104',
    title: 'Raw Wildflower Honey',
    type: 'ADULTERATION',
    date: '2026-08-20',
    status: 'Completed',
    riskLevel: 'LOW',
    summary: 'No added sugar syrup or chalk impurities detected.',
  },
];

export const mockPackagedReports: Record<string, PackagedReportData> = {
  'scan-101': {
    id: 'scan-101',
    productName: 'Organic Unsweetened Almond Milk',
    brand: 'NaturaBlend',
    category: 'Plant-based Dairy Alternative',
    scanDate: '2026-08-28',
    qualityScore: 88,
    verdict: 'Healthy Choice',
    ingredientAnalysis: {
      totalIngredients: 5,
      naturalCount: 4,
      processedCount: 1,
      additiveCount: 1,
      ingredientsList: [
        'Filtered Water',
        'Organic Almonds (3.5%)',
        'Calcium Carbonate',
        'Sea Salt',
        'Gellan Gum',
      ],
    },
    nutritionSummary: {
      calories: '30 kcal / 100ml',
      sugars: '0.1g / 100ml',
      sodium: '70mg / 100ml',
      transFat: '0g',
      protein: '1.2g / 100ml',
      healthRating: 'Good',
    },
    harmfulAdditives: [
      {
        name: 'Gellan Gum',
        eNumber: 'E418',
        severity: 'LOW',
        description: 'Common thickening and stabilizing agent. Safe in small amounts.',
      },
    ],
    healthierAlternatives: [
      {
        id: 'alt-1',
        name: 'Fresh Homemade Almond Extract Base',
        brand: 'PurePure',
        score: 95,
        reason: 'Zero added thickeners or shelf-life extenders.',
      },
    ],
    explainableReasoning: {
      summary: 'This product passes high quality benchmarks with 0g added sugar and minimal processing.',
      positives: [
        'Zero added sugars or high fructose syrup',
        'Fortified with clean Calcium Carbonate',
        'Low caloric density with clean ingredients list',
      ],
      concerns: [
        'Contains minor emulsifier (Gellan Gum) which may cause light discomfort for sensitive gut types.',
      ],
      scientificBasis: 'Evaluated against WHO & FSSAI nutritional guidelines for plant milk substitutes.',
    },
  },
  'scan-103': {
    id: 'scan-103',
    productName: 'Crispy Tangy Potato Chips',
    brand: 'SnackoCorp',
    category: 'Packaged Savory Snack',
    scanDate: '2026-08-25',
    qualityScore: 42,
    verdict: 'Poor Choice / Unhealthy',
    ingredientAnalysis: {
      totalIngredients: 14,
      naturalCount: 4,
      processedCount: 6,
      additiveCount: 4,
      ingredientsList: [
        'Potatoes',
        'Refined Palm Olein Oil',
        'Maltodextrin',
        'Iodized Salt',
        'Monosodium Glutamate (MSG - E621)',
        'Acidity Regulators (E330, E334)',
        'Anti-caking agent (E551)',
        'Artificial Flavoring Substances',
      ],
    },
    nutritionSummary: {
      calories: '540 kcal / 100g',
      sugars: '4.5g / 100g',
      sodium: '890mg / 100g',
      transFat: '0.4g / 100g',
      protein: '6.0g / 100g',
      healthRating: 'High Risk',
    },
    harmfulAdditives: [
      {
        name: 'Monosodium Glutamate',
        eNumber: 'E621',
        severity: 'HIGH',
        description: 'Flavor enhancer that may trigger hyper-reactivity or headaches in sensitive individuals.',
      },
      {
        name: 'Refined Palm Oil',
        severity: 'MEDIUM',
        description: 'High saturated fat ratio linked to increased LDL cholesterol level.',
      },
      {
        name: 'Silicon Dioxide',
        eNumber: 'E551',
        severity: 'LOW',
        description: 'Anti-caking agent allowed in strictly regulated ultra-low dosages.',
      },
    ],
    healthierAlternatives: [
      {
        id: 'alt-2',
        name: 'Air-Baked Sweet Potato Chips',
        brand: 'HealthyBites',
        score: 82,
        reason: 'Baked in cold-pressed coconut oil with 60% less sodium and no MSG.',
      },
      {
        id: 'alt-3',
        name: 'Vacuum Fried Roasted Makhana (Foxnuts)',
        brand: 'NutriRoots',
        score: 91,
        reason: 'High protein, minimal oil, zero artificial flavor enhancers.',
      },
    ],
    explainableReasoning: {
      summary: 'High sodium content and artificial flavor enhancers degrade this product rating significantly.',
      positives: ['Made from real potato slices.'],
      concerns: [
        'Exceeds 40% recommended daily allowance for sodium in a single serving.',
        'Contains Palm Oil and multiple synthetic additives.',
      ],
      scientificBasis: 'Calculated using Nova Food Classification for ultra-processed food profiling.',
    },
  },
};

export const mockAdulterationGuides: Record<string, AdulterationTestGuide> = {
  ghee: {
    id: 'ghee',
    foodName: 'Desi Ghee / Butter',
    commonAdulterants: ['Vanaspati (Hydrogenated Oil)', 'Starch / Mashed Potatoes', 'Animal Fats'],
    materialsRequired: [
      'Glass test tube or small clear glass container',
      'Concentrated Hydrochloric Acid (HCl) or Iodine Solution',
      'Pinch of sugar (if testing Vanaspati)',
      '1 tsp Pure / Sample Ghee',
    ],
    steps: [
      {
        stepNumber: 1,
        title: 'Melt the Sample',
        description: 'Take 1 teaspoon of melted Ghee or Butter sample into a clean, dry glass vessel.',
        tip: 'Ensure the ghee is fully liquified at room temperature before adding reagents.',
        estimatedTime: '1 min',
      },
      {
        stepNumber: 2,
        title: 'Add Hydrochloric Acid & Sugar (Baudouin Test)',
        description: 'Add 5 ml of HCl along with a small pinch of sugar. Shake the mixture vigorously for 1 minute.',
        tip: 'Handle HCl with care or use gentle household testing reagents if provided in kit.',
        estimatedTime: '2 mins',
      },
      {
        stepNumber: 3,
        title: 'Observe Layer Separation',
        description: 'Allow the mixture to settle for 5 minutes. Watch for color change in the bottom acid layer.',
        tip: 'Crimson or deep pink color indicates presence of Vanaspati adulteration.',
        estimatedTime: '5 mins',
      },
      {
        stepNumber: 4,
        title: 'Capture Clear Photo of Result',
        description: 'Place the test tube in bright natural light and take a high-resolution photo from the front.',
        estimatedTime: '1 min',
      },
    ],
  },
  honey: {
    id: 'honey',
    foodName: 'Pure Honey',
    commonAdulterants: ['Invert Sugar Syrup', 'Corn Syrup', 'Chalk Powder', 'Water Dilution'],
    materialsRequired: [
      'Glass of clean water',
      'Cotton wick / matchstick',
      '1 tsp Honey sample',
    ],
    steps: [
      {
        stepNumber: 1,
        title: 'Water Dispersion Test',
        description: 'Drop 1 spoonful of honey into a glass of clean room-temperature water without stirring.',
        tip: 'Pure honey settles at the bottom in a thick blob; adulterated honey dissolves rapidly.',
        estimatedTime: '1 min',
      },
      {
        stepNumber: 2,
        title: 'Flame / Cotton Wick Test',
        description: 'Dip a cotton wick in honey and light it with a matchstick.',
        tip: 'Pure honey burns readily. If water is present, crackling sound occurs or it won\'t ignite.',
        estimatedTime: '2 mins',
      },
      {
        stepNumber: 3,
        title: 'Photograph Water Settling Pattern',
        description: 'Take a close-up photo of the honey at the bottom of the glass.',
        estimatedTime: '1 min',
      },
    ],
  },
  milk: {
    id: 'milk',
    foodName: 'Fresh Milk',
    commonAdulterants: ['Synthetic Milk', 'Urea', 'Detergent', 'Starch / Water'],
    materialsRequired: [
      'Polished slanted surface (glass or tile)',
      'Red Litmus or Iodine drops',
      'Sample Milk',
    ],
    steps: [
      {
        stepNumber: 1,
        title: 'Slanted Drop Flow Test',
        description: 'Put a drop of milk on a slanting polished glass surface.',
        tip: 'Pure milk flows slowly leaving a white trail behind. Watered milk flows quickly without leaving a trace.',
        estimatedTime: '1 min',
      },
      {
        stepNumber: 2,
        title: 'Starch Iodine Test',
        description: 'Add 2 drops of Iodine solution to 5ml milk sample.',
        tip: 'Turning blue indicates starch addition.',
        estimatedTime: '2 mins',
      },
      {
        stepNumber: 3,
        title: 'Capture Clear Image of Reaction',
        description: 'Photograph the test reaction under good lighting.',
        estimatedTime: '1 min',
      },
    ],
  },
};

export const mockAdulterationReports: Record<string, AdulterationReportData> = {
  'scan-102': {
    id: 'scan-102',
    foodName: 'Desi Ghee Sample A',
    category: 'Fats & Oils',
    testDate: '2026-08-27',
    riskLevel: 'HIGH',
    confidenceScore: 92,
    detectedAdulterants: ['Hydrogenated Vegetable Oil (Vanaspati)', 'Traces of Starch'],
    aiObservations: {
      visualMarker: 'Dark crimson red spectrum detected at the lower acid separation layer during Baudouin chemical test.',
      chemicalIndicator: 'Strong optical absorption consistent with synthetic dye / trans-fatty acid binders.',
      textureNote: 'Granular lump dispersion anomaly noted during thermal melt phase.',
    },
    recommendedNextAction: 'DO NOT CONSUME. Report batch lot number to local regulatory authorities (FSSAI/FDA) and request seller refund.',
    disclaimer: 'FraudShield AI preliminary screening is a rapid detection aid. For legal proceedings, lab verification via GC-MS is recommended.',
  },
  'scan-104': {
    id: 'scan-104',
    foodName: 'Raw Wildflower Honey',
    category: 'Sweeteners',
    testDate: '2026-08-20',
    riskLevel: 'LOW',
    confidenceScore: 96,
    detectedAdulterants: [],
    aiObservations: {
      visualMarker: 'Distinct cohesive lump formation at glass base with zero high-velocity water dissolution.',
      chemicalIndicator: 'Refractive index and viscosity profile match natural raw unheated honey parameters.',
      textureNote: 'Natural micro-crystallization structure present without artificial sugar layering.',
    },
    recommendedNextAction: 'Safe for consumption. Meets quality criteria for pure unrefined honey.',
    disclaimer: 'FraudShield AI preliminary screening is a rapid detection aid.',
  },
};
