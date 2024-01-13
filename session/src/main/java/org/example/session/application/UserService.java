package org.example.session.application;

import lombok.RequiredArgsConstructor;
import org.example.session.domain.user.User;
import org.example.session.domain.user.UserRole;
import org.example.session.infrastructure.repository.UserRepository;
import org.example.session.infrastructure.web.request.UserSignupRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 사용자 이름입니다. 전달 받은 사용자 이름 : " + username));
    }

    public void signup(UserSignupRequest request) {
        String password = passwordEncoder.encode(request.password());

        User user = User.builder()
                .username(request.username())
                .password(password)
                .role(UserRole.MEMBER)
                .build();

        userRepository.save(user);
    }
}
