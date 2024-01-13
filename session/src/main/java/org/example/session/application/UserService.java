package org.example.session.application;

import lombok.RequiredArgsConstructor;
import org.example.session.domain.user.User;
import org.example.session.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 사용자 이름입니다. 전달 받은 사용자 이름 : " + username));
    }
}
