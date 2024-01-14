package org.example.session.infrastructure.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.example.session.domain.user.LoginUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/healthy")
@Slf4j
public class HealthApi {

    @GetMapping
    public ResponseEntity<Boolean> isHealthy(@AuthenticationPrincipal LoginUser user) {
        log.info("{}", user.getUsername());
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
