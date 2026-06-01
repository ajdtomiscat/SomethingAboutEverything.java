
package com.example.secondhand.mapper;

import com.example.secondhand.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    int insert(Product product);
    Product findById(Long id);
    List<Product> findByUserId(Long userId);
    List<Product> findByCategoryId(Long categoryId);
    List<Product> search(@Param("keyword") String keyword);
    List<Product> findAll();
    int update(Product product);
    int delete(Long id);
    int incrementViews(Long id);
}
