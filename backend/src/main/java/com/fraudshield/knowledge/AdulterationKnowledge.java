package com.fraudshield.knowledge;

import com.fraudshield.scoring.RiskLevel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "adulteration_knowledge")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdulterationKnowledge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String foodCategory;

    @Column(length = 2000)
    private String description;

    @Column(length = 2000)
    private String commonAdulterants;

    @Column(length = 2000)
    private String homeTestMethod;

    @Column(length = 2000)
    private String healthImpacts;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RiskLevel defaultSeverity;

    @Column
    private String regulatoryLimits;

    @Builder.Default
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}
