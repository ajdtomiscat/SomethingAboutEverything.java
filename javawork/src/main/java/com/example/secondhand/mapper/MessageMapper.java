
package com.example.secondhand.mapper;

import com.example.secondhand.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MessageMapper {
    int insert(Message message);
    Message findById(Long id);
    List<Message> findByFromUserId(Long fromUserId);
    List<Message> findByToUserId(Long toUserId);
    List<Message> findConversation(@Param("userId1") Long userId1, @Param("userId2") Long userId2);
    int update(Message message);
    int updateReadStatus(Long toUserId);
    int delete(Long id);
    List<Message> findConversations(@Param("userId") Long userId);
}
