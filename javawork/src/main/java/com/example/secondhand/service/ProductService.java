
package com.example.secondhand.service;

import com.example.secondhand.entity.Product;

import java.util.List;

public interface ProductService {
    Product create(Product product);
    Product findById(Long id);
    List<Product> findByUserId(Long userId);
    List<Product> findByCategoryId(Long categoryId);
    List<Product> search(String keyword);
    List<Product> findAll();
    void update(Product product);
    void delete(Long id);
    void incrementViews(Long id);
}
