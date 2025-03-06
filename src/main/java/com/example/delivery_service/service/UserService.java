package com.example.delivery_service.service;

import com.example.delivery_service.entity.User;
import com.example.delivery_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {

    private final UserRepository userRepository;


    private final PasswordEncoder passwordEncoder;

    public void registerUser(String username, String email, String password) {
        log.info("회원가입 요청 - username: {}, email: {}", username, email);

        if (userRepository.existsByUsername(username)) {
            log.warn("이미 존재하는 사용자 이름: {}", username);
            throw new IllegalArgumentException("이미 존재하는 사용자 이름입니다.");
        }

        if (userRepository.existsByEmail(email)) {
            log.warn("이미 존재하는 이메일: {}", email);
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        log.info("비밀번호 암호화 중...");
        String encodedPassword = passwordEncoder.encode(password);

        log.info("새로운 사용자 객체 생성...");
        User user = new User(username, email, encodedPassword);

        log.info("DB에 사용자 저장 중...");
        userRepository.save(user);

        log.info("회원가입 성공 - username: {}", username);
    }




}
