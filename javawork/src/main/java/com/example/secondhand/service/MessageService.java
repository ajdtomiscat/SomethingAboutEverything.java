
package com.example.secondhand.service;

import com.example.secondhand.entity.Message;

import java.util.List;

public interface MessageService {
    Message send(Message message);
    List<Message> findByToUserId(Long toUserId);
    List<Message> findConversation(Long userId1, Long userId2);
    void markAsRead(Long toUserId);
    List<Message> findConversations(Long userId);
}
