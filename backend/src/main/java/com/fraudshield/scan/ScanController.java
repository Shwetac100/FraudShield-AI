package com.fraudshield.scan;

import com.fraudshield.common.dto.ApiResponse;
import com.fraudshield.scan.dto.CreateScanRequest;
import com.fraudshield.scan.dto.ScanResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/scans")
public class ScanController {

    private final ScanService scanService;

    public ScanController(ScanService scanService) {
        this.scanService = scanService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ScanResponse>> createScan(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody CreateScanRequest request
    ) {
        ScanResponse response = scanService.createScan(userDetails.getUsername(), request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Scan created and processed successfully", response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ScanResponse>> getScanById(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long id
    ) {
        ScanResponse response = scanService.getScanById(id, userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success("Scan details retrieved successfully", response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ScanResponse>>> getUserScanHistory(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        List<ScanResponse> history = scanService.getUserScanHistory(userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success("Scan history retrieved successfully", history));
    }
}
