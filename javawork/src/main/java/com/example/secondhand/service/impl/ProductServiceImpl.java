
package com.example.secondhand.service.impl;

import com.example.secondhand.entity.Product;
import com.example.secondhand.mapper.ProductMapper;
import com.example.secondhand.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product create(Product product) {
        product.setStatus(1);
        product.setViews(0);
        productMapper.insert(product);
        return product;
    }

    @Override
    public Product findById(Long id) {
        return productMapper.findById(id);
    }

    @Override
    public List<Product> findByUserId(Long userId) {
        return productMapper.findByUserId(userId);
    }

    @Override
    public List<Product> findByCategoryId(Long categoryId) {
        return productMapper.findByCategoryId(categoryId);
    }

    @Override
    public List<Product> search(String keyword) {
        return productMapper.search(keyword);
    }

    @Override
    public List<Product> findAll() {
        return productMapper.findAll();
    }

    @Override
    public void update(Product product) {
        productMapper.update(product);
    }

    @Override
    public void delete(Long id) {
        productMapper.delete(id);
    }

    @Override
    public void incrementViews(Long id) {
        productMapper.incrementViews(id);
    }
}
