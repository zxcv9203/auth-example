package org.example.session.infrastructure.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.session.infrastructure.security.request.LoginRequest;
import org.example.session.infrastructure.security.response.LoginFailResponse;
import org.example.session.infrastructure.security.response.LoginResponse;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class LoginFilter extends OncePerRequestFilter {

    public static final String LOGIN_PATH = "/api/login";

    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (HttpMethod.POST.matches(request.getMethod()) && LOGIN_PATH.equals(request.getRequestURI())) {
            try {
                LoginRequest loginRequest = objectMapper.readValue(request.getInputStream(), LoginRequest.class);

                UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.username(), loginRequest.password());
                Authentication authenticate = authenticationManager.authenticate(token);

                SecurityContextHolder.getContext().setAuthentication(authenticate);

                HttpSession session = request.getSession(true);
                session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

                response.setStatus(HttpServletResponse.SC_OK);
                response.setCharacterEncoding("utf8");
                response.setContentType("application/json");
                response.getWriter().write(objectMapper.writeValueAsString(new LoginResponse("로그인에 성공했습니다.")));
                return;
            } catch (RuntimeException e) {
                log.warn(e.getMessage());
                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setCharacterEncoding("utf-8");
                response.getWriter().write(objectMapper.writeValueAsString(new LoginFailResponse("인증에 실패했습니다. ID나 패스워드를 확인해주세요.")));
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
