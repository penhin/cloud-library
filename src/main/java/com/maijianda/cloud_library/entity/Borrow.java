package com.maijianda.cloud_library.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Borrow {
    private Long borrowId;
    private Long userId;
    private Long bookId;
    private LocalDateTime borrowTime;
    private LocalDateTime returnTime;
    private String status; // BORROWED, RETURNED, CONFIRMED
}
