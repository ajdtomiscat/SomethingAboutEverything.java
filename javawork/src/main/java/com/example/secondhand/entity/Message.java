
package com.example.secondhand.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Message {
    private Long id;
    private Long fromUserId;
    private Long toUserId;
    private String content;
    private Integer isRead;
    private LocalDateTime createdAt;
    
    private User fromUser;
    private User toUser;

    private String fromUserNickname;
    private String fromUserAvatar;
    private String toUserNickname;
    private String toUserAvatar;
}
