package com.secure.vivaran.services.impl;

import com.secure.vivaran.services.AIService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import java.util.Map;

@Service
@Validated
public class AIServiceImpl implements AIService {
    private final String geminiApiUrl;
    private final String geminiApiKey;
    private final WebClient webClient;

    public AIServiceImpl(
            @Value("${gemini.api.url}") String geminiApiUrl,
            @Value("${gemini.api.key}") String geminiApiKey,
            WebClient.Builder webClientBuilder) {

        if (geminiApiUrl == null || geminiApiKey == null) {
            throw new IllegalStateException("Gemini API URL and API Key must be configured");
        }

        this.geminiApiUrl = geminiApiUrl;
        this.geminiApiKey = geminiApiKey;
        this.webClient = webClientBuilder.build();
    }

    @Override
    public String getAnswer(String question) {
        if (question == null || question.trim().isEmpty()) {
            throw new IllegalArgumentException("Question cannot be null or empty");
        }

        Map<String, Object> parts = Map.of("text", question);
        Map<String, Object> content = Map.of("parts", new Object[]{parts});
        Map<String, Object> requestBody = Map.of("contents", new Object[]{content});

        try {
            return webClient.post()
                    .uri(geminiApiUrl + geminiApiKey)
                    .header("Content-Type", "application/json")
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (WebClientException e) {
            throw new RuntimeException("Error calling Gemini API: " + e.getMessage(), e);
        }
    }
}