package com.fraudshield.scan;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "adulteration_scan_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdulterationScanDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "scan_id", nullable = false, unique = true)
    private Scan scan;

    @Column(nullable = false)
    private String foodCategory;

    @Column(nullable = false)
    private String testType;

    @Column(length = 2000, nullable = false)
    private String userObservations;

    @Column
    private String suspectedAdulterant;

    @Column
    private Boolean testPositive;
}
