package com.fraudshield.ai;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fraudshield.ai.dto.AiAnalysisResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AiOcrService {

    @Value("${gemini.api-key:}")
    private String geminiApiKey;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public AiAnalysisResult analyzeFoodLabel(String imageUrl, String rawText) {
        if (geminiApiKey != null && !geminiApiKey.isBlank()) {
            try {
                return callGeminiApi(imageUrl, rawText);
            } catch (Exception e) {
                // System.err.println("Gemini API call failed, using deterministic fallback: " + e.getMessage());
            }
        }
        return processFallback(imageUrl, rawText);
    }

    private AiAnalysisResult callGeminiApi(String imageUrl, String inputRawText) throws Exception {
        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + geminiApiKey;

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String prompt = "You are an expert food safety inspector AI. Extract structured information from this input text or image.\n" +
                "Text input: " + (inputRawText != null ? inputRawText : "Image URL: " + imageUrl) + "\n\n" +
                "Respond strictly with a raw JSON object (no markdown, no ```json ``` fences) with the following structure:\n" +
                "{\n" +
                "  \"productName\": \"extracted product name\",\n" +
                "  \"ingredientsText\": \"comma separated list of ingredients\",\n" +
                "  \"nutritionalInfo\": \"energy, fat, sugar, protein details if any\",\n" +
                "  \"detectedENumbers\": [\"E102\", \"E211\"],\n" +
                "  \"detectedHarmfulAdditives\": [\"Tartrazine\", \"Benzoic Acid\"]\n" +
                "}";

        Map<String, Object> requestBody = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(Map.of("text", prompt)))
                )
        );

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            JsonNode rootNode = objectMapper.readTree(response.getBody());
            String textResponse = rootNode.path("candidates").get(0).path("content").path("parts").get(0).path("text").asText();

            String cleanJson = textResponse.replaceAll("```json", "").replaceAll("```", "").trim();
            JsonNode parsed = objectMapper.readTree(cleanJson);

            List<String> eNumbers = new ArrayList<>();
            if (parsed.has("detectedENumbers")) {
                parsed.get("detectedENumbers").forEach(node -> eNumbers.add(node.asText()));
            }

            List<String> harmful = new ArrayList<>();
            if (parsed.has("detectedHarmfulAdditives")) {
                parsed.get("detectedHarmfulAdditives").forEach(node -> harmful.add(node.asText()));
            }

            return AiAnalysisResult.builder()
                    .productName(parsed.path("productName").asText("Scanned Product"))
                    .ingredientsText(parsed.path("ingredientsText").asText(""))
                    .nutritionalInfo(parsed.path("nutritionalInfo").asText(""))
                    .detectedENumbers(eNumbers)
                    .detectedHarmfulAdditives(harmful)
                    .rawAiResponse(textResponse)
                    .isMocked(false)
                    .build();
        }

        return processFallback(imageUrl, inputRawText);
    }

    public AiAnalysisResult processFallback(String imageUrl, String rawText) {
        String content = (rawText != null ? rawText : "").trim();

        String productName = "Scanned Food Product";
        if (!content.isEmpty()) {
            String[] lines = content.split("\n");
            if (lines.length > 0 && !lines[0].isBlank()) {
                productName = lines[0].trim();
            }
        }

        List<String> eNumbers = extractENumbersFromText(content);
        List<String> harmfulAdditives = extractHarmfulAdditivesFromText(content);

        return AiAnalysisResult.builder()
                .productName(productName)
                .ingredientsText(content.isEmpty() ? "Ingredients list from image" : content)
                .nutritionalInfo("Serving Size: 100g | Energy: 250 kcal | Sugar: 12g | Sodium: 340mg")
                .detectedENumbers(eNumbers)
                .detectedHarmfulAdditives(harmfulAdditives)
                .rawAiResponse("Deterministic OCR Processing Completed")
                .isMocked(true)
                .build();
    }

    private List<String> extractENumbersFromText(String text) {
        List<String> list = new ArrayList<>();
        if (text == null || text.isBlank()) return list;

        Pattern pattern = Pattern.compile("E\\s?\\d{3,4}[a-z]?", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String match = matcher.group().replaceAll("\\s+", "").toUpperCase();
            if (!list.contains(match)) {
                list.add(match);
            }
        }
        return list;
    }

    private List<String> extractHarmfulAdditivesFromText(String text) {
        List<String> list = new ArrayList<>();
        if (text == null || text.isBlank()) return list;

        String upper = text.toUpperCase();
        List<String> keywords = List.of("TARTRAZINE", "METANIL YELLOW", "SODIUM BENZOATE", "PALM OIL", "MONOSODIUM GLUTAMATE", "MSG", "RHODAMINE");
        for (String kw : keywords) {
            if (upper.contains(kw)) {
                list.add(kw);
            }
        }
        return list;
    }
}
