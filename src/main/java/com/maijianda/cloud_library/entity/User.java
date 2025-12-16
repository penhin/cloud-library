package com.maijianda.cloud_library.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class User {
    private Long userId;
    private String username;
    private String password;
    private String role;
    private LocalDateTime createdAt;
}
