package com.ecommerce.payment_service.mapper;
import com.ecommerce.payment_service.dto.PaymentRequestDTO;
import com.ecommerce.payment_service.dto.PaymentResponseDTO;
import com.ecommerce.payment_service.entity.Payment;

public class PaymentMapper {

    public static PaymentRequestDTO toRequestDto(Payment entity) {
        return PaymentRequestDTO.builder()
                .orderId(entity.getOrderId())
                .amount(entity.getAmount())
                .currency(entity.getCurrency())
                .paymentMethod(entity.getPaymentMethod())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public static PaymentResponseDTO toResponseDto(Payment entity) {
        return PaymentResponseDTO.builder()
                .id(entity.getId())
                .orderId(entity.getOrderId())
                .amount(entity.getAmount())
                .currency(entity.getCurrency())
                .paymentMethod(entity.getPaymentMethod())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

}
