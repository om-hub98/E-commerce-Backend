package com.ecommerce.orderMS.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDTO {

    private String productId;
    private String productName;
    private Double priceAtPurchase;
    private Integer quantity;
}
