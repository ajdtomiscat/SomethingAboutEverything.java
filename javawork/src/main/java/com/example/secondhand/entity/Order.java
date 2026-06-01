
package com.example.secondhand.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Order {
    private Long id;
    private Long buyerId;
    private Long sellerId;
    private Long productId;
    private BigDecimal price;
    private Integer status;
    private String address;
    private String remark;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    private User buyer;
    private User seller;
    private Product product;

    private String productTitle;
    private String productImages;
    private String sellerNickname;
    private String buyerNickname;
}
