package org.example.session.infrastructure.web.request;

public record UserSignupRequest(
        String username,
        String password
){
}
