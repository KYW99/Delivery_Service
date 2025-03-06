package com.example.delivery_service.controller;

import com.example.delivery_service.dto.UserDTO;
import com.example.delivery_service.entity.User;
import com.example.delivery_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Log4j2
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody UserDTO userDTO) {
        try {
            userService.registerUser(userDTO.getUsername(), userDTO.getEmail(), userDTO.getPassword());
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "회원가입 성공");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
