
package com.example.secondhand.mapper;

import com.example.secondhand.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    int insert(Category category);
    Category findById(Long id);
    List<Category> findAll();
    int update(Category category);
    int delete(Long id);
}
