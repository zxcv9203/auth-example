package org.example.jwt.infrastructure.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HealthApi {

    @GetMapping("/health")
    public ResponseEntity<Boolean> isHealthy() {
        return ResponseEntity.ok(true);
    }
}
