package com.fraudshield.scan;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "packaged_scan_details")
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

    public PackagedScanDetails() {
    }

    public PackagedScanDetails(Long id, Scan scan, String rawText, String ingredientsText, String nutritionalInfo, String detectedENumbers, String detectedHarmfulAdditives) {
        this.id = id;
        this.scan = scan;
        this.rawText = rawText;
        this.ingredientsText = ingredientsText;
        this.nutritionalInfo = nutritionalInfo;
        this.detectedENumbers = detectedENumbers;
        this.detectedHarmfulAdditives = detectedHarmfulAdditives;
    }

    public static PackagedScanDetailsBuilder builder() {
        return new PackagedScanDetailsBuilder();
    }

    public static class PackagedScanDetailsBuilder {
        private Long id;
        private Scan scan;
        private String rawText;
        private String ingredientsText;
        private String nutritionalInfo;
        private String detectedENumbers;
        private String detectedHarmfulAdditives;

        PackagedScanDetailsBuilder() {
        }

        public PackagedScanDetailsBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PackagedScanDetailsBuilder scan(Scan scan) {
            this.scan = scan;
            return this;
        }

        public PackagedScanDetailsBuilder rawText(String rawText) {
            this.rawText = rawText;
            return this;
        }

        public PackagedScanDetailsBuilder ingredientsText(String ingredientsText) {
            this.ingredientsText = ingredientsText;
            return this;
        }

        public PackagedScanDetailsBuilder nutritionalInfo(String nutritionalInfo) {
            this.nutritionalInfo = nutritionalInfo;
            return this;
        }

        public PackagedScanDetailsBuilder detectedENumbers(String detectedENumbers) {
            this.detectedENumbers = detectedENumbers;
            return this;
        }

        public PackagedScanDetailsBuilder detectedHarmfulAdditives(String detectedHarmfulAdditives) {
            this.detectedHarmfulAdditives = detectedHarmfulAdditives;
            return this;
        }

        public PackagedScanDetails build() {
            return new PackagedScanDetails(id, scan, rawText, ingredientsText, nutritionalInfo, detectedENumbers, detectedHarmfulAdditives);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Scan getScan() {
        return scan;
    }

    public void setScan(Scan scan) {
        this.scan = scan;
    }

    public String getRawText() {
        return rawText;
    }

    public void setRawText(String rawText) {
        this.rawText = rawText;
    }

    public String getIngredientsText() {
        return ingredientsText;
    }

    public void setIngredientsText(String ingredientsText) {
        this.ingredientsText = ingredientsText;
    }

    public String getNutritionalInfo() {
        return nutritionalInfo;
    }

    public void setNutritionalInfo(String nutritionalInfo) {
        this.nutritionalInfo = nutritionalInfo;
    }

    public String getDetectedENumbers() {
        return detectedENumbers;
    }

    public void setDetectedENumbers(String detectedENumbers) {
        this.detectedENumbers = detectedENumbers;
    }

    public String getDetectedHarmfulAdditives() {
        return detectedHarmfulAdditives;
    }

    public void setDetectedHarmfulAdditives(String detectedHarmfulAdditives) {
        this.detectedHarmfulAdditives = detectedHarmfulAdditives;
    }
}
