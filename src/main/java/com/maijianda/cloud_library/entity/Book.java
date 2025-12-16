package com.maijianda.cloud_library.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Book {
    private Long bookId;
    private String title;
    private String author;
    private String publisher;
    private Integer totalStock;
    private Integer availableStock;
    private LocalDateTime createdAt;
}
