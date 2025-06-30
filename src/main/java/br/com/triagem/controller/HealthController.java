package br.com.triagem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<String> verificarStatus() {
        return ResponseEntity.ok("✅ Sistema no ar!");
    }
}
