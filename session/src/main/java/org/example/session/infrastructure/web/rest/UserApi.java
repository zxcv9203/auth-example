package org.example.session.infrastructure.web.rest;

import lombok.RequiredArgsConstructor;
import org.example.session.application.UserService;
import org.example.session.infrastructure.web.request.UserSignupRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApi {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> signup(
            @RequestBody UserSignupRequest request
    ) {
        userService.signup(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
