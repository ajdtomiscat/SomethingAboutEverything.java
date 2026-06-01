
package com.example.secondhand.service;

import com.example.secondhand.entity.Review;

import java.util.List;

public interface ReviewService {
    Review create(Review review);
    Review findById(Long id);
    Review findByOrderId(Long orderId);
    List<Review> findByProductId(Long productId);
    void update(Review review);
    void delete(Long id);
}
