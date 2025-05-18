package com.ecommerce.orderMS.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderRequestDTO {

    private Long customerId;
    private BigDecimal totalAmount;
    private Long paymentId;
    private String orderStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<OrderItemDTO> items;
}
