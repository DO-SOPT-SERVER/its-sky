package org.sopt.Seminar.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.sopt.Seminar.domain.user.dto.request.UserRequest;
import org.sopt.Seminar.domain.user.model.User;
import org.sopt.Seminar.domain.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public String create(UserRequest request) {
        User user = User.builder()
                .nickname(request.nickname())
                .password(passwordEncoder.encode(request.password()))
                .build();
        userRepository.save(user);

        return user.getId().toString();
    }

    public void signIn(UserRequest request) {
        User user = userRepository.findByNickname(request.nickname())
                .orElseThrow(() -> new RuntimeException("해당하는 회원이 존재하지 않습니다."));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
    }
}
