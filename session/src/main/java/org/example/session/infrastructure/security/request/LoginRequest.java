package org.example.session.infrastructure.security.request;

public record LoginRequest(
        String username,
        String password
) {
}
