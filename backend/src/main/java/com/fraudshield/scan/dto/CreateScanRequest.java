package com.fraudshield.scan.dto;

import com.fraudshield.scan.ScanType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateScanRequest {

    @NotNull(message = "Scan type is required")
    private ScanType scanType;

    private String productName;
    private String imageUrl;

    // For PACKAGED scan type
    private String rawIngredientsText;

    // For ADULTERATION scan type
    private String foodCategory;
    private String testType;
    private String userObservations;
    private Boolean testPositive;
}
