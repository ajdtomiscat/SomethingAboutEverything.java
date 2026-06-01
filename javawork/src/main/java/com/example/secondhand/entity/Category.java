
package com.example.secondhand.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Category {
    private Long id;
    private String name;
    private String icon;
    private LocalDateTime createdAt;
}
