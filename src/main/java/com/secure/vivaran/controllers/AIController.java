package com.secure.vivaran.controllers;

import com.secure.vivaran.services.AIService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/public/chatAI")
public class AIController {
    private final AIService aiService;
    @PostMapping("/ask")
    public ResponseEntity<String> askQuestion(@RequestParam Map<String,String> payload){
        String question = payload.get("question");
        String answer = aiService.getAnswer(question);
        return ResponseEntity.ok(answer);
    }
}
