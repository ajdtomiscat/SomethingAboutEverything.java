
package com.example.secondhand.service;

import com.example.secondhand.entity.User;

public interface UserService {
    User register(User user);
    User login(String username, String password);
    User findById(Long id);
    User findByUsername(String username);
    void update(User user);
}
