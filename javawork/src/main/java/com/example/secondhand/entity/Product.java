
package com.example.secondhand.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Product {
    private Long id;
    private Long userId;
    private Long categoryId;
    private String title;
    private String description;
    private BigDecimal price;
    private BigDecimal originalPrice;
    private String images;
    private Integer status;
    private Integer views;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    private User user;
    private Category category;
    
    private String userNickname;
    private String userAvatar;
    private String categoryName;
}
