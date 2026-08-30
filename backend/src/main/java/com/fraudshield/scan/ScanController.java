package com.fraudshield.scan;

import com.fraudshield.common.dto.ApiResponse;
import com.fraudshield.scan.dto.CreateScanRequest;
import com.fraudshield.scan.dto.ScanResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/scans")
@Tag(name = "Scans", description = "Endpoints for creating and retrieving food scans")
@SecurityRequirement(name = "BearerAuthentication")
public class ScanController {

    private final ScanService scanService;

    public ScanController(ScanService scanService) {
        this.scanService = scanService;
    }

    @PostMapping
    @Operation(summary = "Create food scan", description = "Requires authenticated user. Analyzes packaged food ingredients or adulteration test observations.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Scan created and processed successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request payload", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    public ResponseEntity<ApiResponse<ScanResponse>> createScan(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody CreateScanRequest request
    ) {
        ScanResponse response = scanService.createScan(userDetails.getUsername(), request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Scan created and processed successfully", response));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get scan by ID", description = "Requires authenticated user. Retrieves details of a specific scan owned by the user.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Scan details retrieved successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Scan not found or access denied", content = @Content)
    })
    public ResponseEntity<ApiResponse<ScanResponse>> getScanById(
            @AuthenticationPrincipal UserDetails userDetails,
            @Parameter(description = "ID of the scan") @PathVariable Long id
    ) {
        ScanResponse response = scanService.getScanById(id, userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success("Scan details retrieved successfully", response));
    }

    @GetMapping
    @Operation(summary = "Get user scan history", description = "Requires authenticated user. Retrieves all past scans created by the authenticated user.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Scan history retrieved successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    public ResponseEntity<ApiResponse<List<ScanResponse>>> getUserScanHistory(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        List<ScanResponse> history = scanService.getUserScanHistory(userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success("Scan history retrieved successfully", history));
    }
}
