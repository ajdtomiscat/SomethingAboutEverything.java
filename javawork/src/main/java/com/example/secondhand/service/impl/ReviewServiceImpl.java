
package com.example.secondhand.service.impl;

import com.example.secondhand.entity.Order;
import com.example.secondhand.entity.Review;
import com.example.secondhand.mapper.OrderMapper;
import com.example.secondhand.mapper.ReviewMapper;
import com.example.secondhand.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Review create(Review review) {
        Order order = orderMapper.findById(review.getOrderId());
        if (order == null || order.getStatus() != 4) {
            throw new RuntimeException("订单不存在或未完成");
        }
        if (reviewMapper.findByOrderId(review.getOrderId()) != null) {
            throw new RuntimeException("该订单已评价");
        }
        reviewMapper.insert(review);
        return review;
    }

    @Override
    public Review findById(Long id) {
        return reviewMapper.findById(id);
    }

    @Override
    public Review findByOrderId(Long orderId) {
        return reviewMapper.findByOrderId(orderId);
    }

    @Override
    public List<Review> findByProductId(Long productId) {
        return reviewMapper.findByProductId(productId);
    }

    @Override
    public void update(Review review) {
        reviewMapper.update(review);
    }

    @Override
    public void delete(Long id) {
        reviewMapper.delete(id);
    }
}
