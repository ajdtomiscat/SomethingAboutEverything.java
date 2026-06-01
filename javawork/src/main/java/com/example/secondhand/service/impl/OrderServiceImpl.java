
package com.example.secondhand.service.impl;

import com.example.secondhand.entity.Order;
import com.example.secondhand.entity.Product;
import com.example.secondhand.mapper.OrderMapper;
import com.example.secondhand.mapper.ProductMapper;
import com.example.secondhand.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Order create(Order order) {
        Product product = productMapper.findById(order.getProductId());
        if (product == null || product.getStatus() != 1) {
            throw new RuntimeException("商品不存在或已下架");
        }
        order.setSellerId(product.getUserId());
        order.setPrice(product.getPrice());
        order.setStatus(1);
        orderMapper.insert(order);
        product.setStatus(2);
        productMapper.update(product);
        return order;
    }

    @Override
    public Order findById(Long id) {
        return orderMapper.findById(id);
    }

    @Override
    public List<Order> findByBuyerId(Long buyerId) {
        return orderMapper.findByBuyerId(buyerId);
    }

    @Override
    public List<Order> findBySellerId(Long sellerId) {
        return orderMapper.findBySellerId(sellerId);
    }

    @Override
    public void update(Order order) {
        orderMapper.update(order);
    }

    @Override
    public void delete(Long id) {
        orderMapper.delete(id);
    }
}
