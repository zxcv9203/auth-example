package org.example.session.infrastructure.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
public class FilterConfig {
    @Bean
    public LoginFilter loginFilter(AuthenticationManager authenticationManager, ObjectMapper objectMapper) {
        return new LoginFilter(authenticationManager, objectMapper);
    }
}
