
package com.example.secondhand.mapper;

import com.example.secondhand.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int insert(User user);
    User findById(Long id);
    User findByUsername(String username);
    User findByPhone(String phone);
    int update(User user);
    int delete(Long id);
}
