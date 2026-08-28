package com.fraudshield.knowledge;

import com.fraudshield.knowledge.dto.KnowledgeResponse;
import com.fraudshield.scoring.RiskLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KnowledgeService implements CommandLineRunner {

    private final AdulterationKnowledgeRepository repository;

    public List<KnowledgeResponse> searchKnowledge(String query, String category) {
        List<AdulterationKnowledge> list = repository.searchKnowledge(query, category);
        return list.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    private KnowledgeResponse mapToResponse(AdulterationKnowledge k) {
        return KnowledgeResponse.builder()
                .id(k.getId())
                .name(k.getName())
                .foodCategory(k.getFoodCategory())
                .description(k.getDescription())
                .commonAdulterants(k.getCommonAdulterants())
                .homeTestMethod(k.getHomeTestMethod())
                .healthImpacts(k.getHealthImpacts())
                .defaultSeverity(k.getDefaultSeverity())
                .regulatoryLimits(k.getRegulatoryLimits())
                .createdAt(k.getCreatedAt())
                .build();
    }

    @Override
    public void run(String... args) {
        if (repository.count() == 0) {
            seedKnowledgeData();
        }
    }

    private void seedKnowledgeData() {
        List<AdulterationKnowledge> seeds = List.of(
            AdulterationKnowledge.builder()
                .name("Milk Adulteration")
                .foodCategory("Dairy")
                .description("Milk is commonly diluted with water or adulterated with detergent, starch, urea, and synthetic compounds.")
                .commonAdulterants("Water, Detergent, Urea, Starch, Hydrogen Peroxide")
                .homeTestMethod("Drop milk on a slanted smooth surface. Pure milk flows slowly leaving a white trail; adulterated water milk flows immediately without trail.")
                .healthImpacts("Gastrointestinal disorders, kidney damage from urea/heavy chemicals, food poisoning.")
                .defaultSeverity(RiskLevel.HIGH)
                .regulatoryLimits("Zero tolerance for non-permitted additives, urea < 700 ppm max naturally occurring.")
                .build(),
            AdulterationKnowledge.builder()
                .name("Honey Adulteration")
                .foodCategory("Sweeteners")
                .description("Honey is often adulterated with high fructose corn syrup, rice syrup, invert sugar, or cane sugar syrup.")
                .commonAdulterants("Sugar Syrup, High Fructose Corn Syrup, Invert Sugar")
                .homeTestMethod("Dissolve a spoon of honey in a glass of water. Pure honey settles at the bottom without dissolving immediately.")
                .healthImpacts("Increased risk of diabetes, obesity, spike in blood sugar.")
                .defaultSeverity(RiskLevel.MEDIUM)
                .regulatoryLimits("Must be 100% pure bee honey, no added sugars.")
                .build(),
            AdulterationKnowledge.builder()
                .name("Turmeric Powder Adulteration")
                .foodCategory("Spices")
                .description("Turmeric powder is commonly mixed with lead chromate (toxic dye), Metanil yellow dye, or chalk powder.")
                .commonAdulterants("Metanil Yellow, Lead Chromate, Chalk Powder, Sawdust")
                .homeTestMethod("Add a spoon of turmeric to hot water. If it turns dark yellow/red immediately, synthetic dye is present.")
                .healthImpacts("Metanil yellow is neurotoxic and carcinogenic. Lead chromate causes lead poisoning.")
                .defaultSeverity(RiskLevel.HIGH)
                .regulatoryLimits("Metanil Yellow and synthetic dyes strictly prohibited in turmeric powder.")
                .build(),
            AdulterationKnowledge.builder()
                .name("Chili Powder Adulteration")
                .foodCategory("Spices")
                .description("Chili powder may be adulterated with brick powder, sawdust, or non-permitted dyes like Rhodamine B.")
                .commonAdulterants("Brick Powder, Rhodamine B, Red Lead, Sawdust")
                .homeTestMethod("Mix a teaspoon in water. Brick powder or sawdust will settle at the bottom; synthetic dye bleeds red in water.")
                .healthImpacts("Severe stomach disorders, intestinal blockage, cancer risk from chemical dyes.")
                .defaultSeverity(RiskLevel.HIGH)
                .regulatoryLimits("Artificial color prohibited in chili powder under food safety regulations.")
                .build(),
            AdulterationKnowledge.builder()
                .name("E102 Tartrazine")
                .foodCategory("Additives")
                .description("A synthetic lemon yellow azo dye used in processed foods, confectionery, and beverages.")
                .commonAdulterants("E102, Tartrazine")
                .homeTestMethod("Check package label for E102 / Tartrazine in ingredients.")
                .healthImpacts("Allergic reactions, hyperactivity in children, asthma aggravation.")
                .defaultSeverity(RiskLevel.MEDIUM)
                .regulatoryLimits("Must be explicitly declared; restricted dosage in beverages and sweets.")
                .build()
        );
        repository.saveAll(seeds);
    }
}
