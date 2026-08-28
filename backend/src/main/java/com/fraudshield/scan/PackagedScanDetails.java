package com.fraudshield.scan;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "packaged_scan_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PackagedScanDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "scan_id", nullable = false, unique = true)
    private Scan scan;

    @Column(length = 2000)
    private String rawText;

    @Column(length = 2000)
    private String ingredientsText;

    @Column(length = 1000)
    private String nutritionalInfo;

    @Column(length = 1000)
    private String detectedENumbers;

    @Column(length = 1000)
    private String detectedHarmfulAdditives;
}
