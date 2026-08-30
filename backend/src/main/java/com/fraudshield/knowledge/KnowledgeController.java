package com.fraudshield.knowledge;

import com.fraudshield.common.dto.ApiResponse;
import com.fraudshield.knowledge.dto.KnowledgeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/knowledge")
@Tag(name = "Knowledge Base", description = "Endpoints for searching adulteration knowledge and home testing guides")
public class KnowledgeController {

    private final KnowledgeService knowledgeService;

    public KnowledgeController(KnowledgeService knowledgeService) {
        this.knowledgeService = knowledgeService;
    }

    @GetMapping
    @Operation(summary = "Search knowledge base", description = "Public endpoint to query adulteration knowledge base by search query or food category.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Knowledge base retrieved successfully")
    })
    public ResponseEntity<ApiResponse<List<KnowledgeResponse>>> searchKnowledge(
            @Parameter(description = "Search term for food name or adulterant") @RequestParam(required = false) String query,
            @Parameter(description = "Filter by food category") @RequestParam(required = false) String category
    ) {
        List<KnowledgeResponse> results = knowledgeService.searchKnowledge(query, category);
        return ResponseEntity.ok(ApiResponse.success("Knowledge base retrieved successfully", results));
    }
}
