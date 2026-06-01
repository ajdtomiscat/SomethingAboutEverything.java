
package com.example.secondhand.mapper;

import com.example.secondhand.entity.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    int insert(Review review);
    Review findById(Long id);
    Review findByOrderId(Long orderId);
    List<Review> findByUserId(Long userId);
    List<Review> findByProductId(Long productId);
    int update(Review review);
    int delete(Long id);
}
