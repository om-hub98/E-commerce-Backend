package com.ecommerce.payment_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentTransactionResponseDTO {

    private Long id;
    private Long orderId;
    private Long customerId;
    private BigDecimal amount;
    private String currency;
    private String paymentMethod;
    private String status;
    private String transactionReference;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
