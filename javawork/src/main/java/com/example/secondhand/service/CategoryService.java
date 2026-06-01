
package com.example.secondhand.service;

import com.example.secondhand.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(Long id);
}
