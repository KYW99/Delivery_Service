package com.example.delivery_service.controller;

import com.example.delivery_service.dto.UserDTO;
import com.example.delivery_service.entity.User;
import com.example.delivery_service.repository.UserRepository;
import com.example.delivery_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
@Log4j2
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";  // login.html 페이지를 보여줍니다.
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";  // login.html 페이지를 보여줍니다.
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody UserDTO userDTO) {
        try {
            userService.registerUser(userDTO.getUsername(), userDTO.getEmail(), userDTO.getPassword());
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "회원가입 성공");
            return ResponseEntity.ok(response);  // 리디렉션 대신 성공 메시지 반환
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/check-username")
    public ResponseEntity<Map<String, Boolean>> checkUsername(@RequestParam String username) {
        boolean exists = userRepository.existsByUsername(username);
        Map<String, Boolean> response = new HashMap<>();
        response.put("available", !exists);
        return ResponseEntity.ok(response);
    }
}
