package com.ecommerce.payment_service.mapper;
import com.ecommerce.payment_service.dto.PaymentTransactionRequestDTO;
import com.ecommerce.payment_service.dto.PaymentTransactionResponseDTO;
import com.ecommerce.payment_service.entity.PaymentTransaction;
import lombok.Data;

public class MapperDTO {

    public PaymentTransactionRequestDTO toDto(PaymentTransaction entity) {
        return PaymentTransactionRequestDTO.builder()
                .orderId(entity.getOrderId())
                .customerId(entity.getCustomerId())
                .amount(entity.getAmount())
                .currency(entity.getCurrency())
                .paymentMethod(entity.getPaymentMethod())
                .status(entity.getStatus())
                .transactionReference(entity.getTransactionReference())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public PaymentTransactionResponseDTO toResponseDto(PaymentTransaction entity) {
        return PaymentTransactionResponseDTO.builder()
                .id(entity.getId())
                .orderId(entity.getOrderId())
                .customerId(entity.getCustomerId())
                .amount(entity.getAmount())
                .currency(entity.getCurrency())
                .paymentMethod(entity.getPaymentMethod())
                .status(entity.getStatus())
                .transactionReference(entity.getTransactionReference())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

}
