package com.fraudshield.knowledge;

import com.fraudshield.common.dto.ApiResponse;
import com.fraudshield.knowledge.dto.KnowledgeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/knowledge")
public class KnowledgeController {

    private final KnowledgeService knowledgeService;

    public KnowledgeController(KnowledgeService knowledgeService) {
        this.knowledgeService = knowledgeService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<KnowledgeResponse>>> searchKnowledge(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String category
    ) {
        List<KnowledgeResponse> results = knowledgeService.searchKnowledge(query, category);
        return ResponseEntity.ok(ApiResponse.success("Knowledge base retrieved successfully", results));
    }
}
