package com.example.delivery_service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Setter
@Getter
public class UserDTO {
    private String username;
    private String email;
    private String password;
}
