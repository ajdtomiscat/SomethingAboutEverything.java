
package com.example.secondhand.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Review {
    private Long id;
    private Long orderId;
    private Long userId;
    private Integer rating;
    private String content;
    private String images;
    private LocalDateTime createdAt;
    
    private User user;
    private Order order;

    private String userNickname;
}
