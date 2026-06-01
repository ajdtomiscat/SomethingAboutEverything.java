
package com.example.secondhand.service.impl;

import com.example.secondhand.entity.Message;
import com.example.secondhand.mapper.MessageMapper;
import com.example.secondhand.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public Message send(Message message) {
        message.setIsRead(0);
        messageMapper.insert(message);
        return message;
    }

    @Override
    public List<Message> findByToUserId(Long toUserId) {
        return messageMapper.findByToUserId(toUserId);
    }

    @Override
    public List<Message> findConversation(Long userId1, Long userId2) {
        return messageMapper.findConversation(userId1, userId2);
    }

    @Override
    public void markAsRead(Long toUserId) {
        messageMapper.updateReadStatus(toUserId);
    }

    @Override
    public List<Message> findConversations(Long userId) {
        return messageMapper.findConversations(userId);
    }
}
