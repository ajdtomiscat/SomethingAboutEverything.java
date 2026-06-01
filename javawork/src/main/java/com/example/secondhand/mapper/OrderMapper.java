
package com.example.secondhand.mapper;

import com.example.secondhand.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    int insert(Order order);
    Order findById(Long id);
    List<Order> findByBuyerId(Long buyerId);
    List<Order> findBySellerId(Long sellerId);
    int update(Order order);
    int delete(Long id);
}
