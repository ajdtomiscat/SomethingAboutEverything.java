
package com.example.secondhand.service;

import com.example.secondhand.entity.Order;

import java.util.List;

public interface OrderService {
    Order create(Order order);
    Order findById(Long id);
    List<Order> findByBuyerId(Long buyerId);
    List<Order> findBySellerId(Long sellerId);
    void update(Order order);
    void delete(Long id);
}
