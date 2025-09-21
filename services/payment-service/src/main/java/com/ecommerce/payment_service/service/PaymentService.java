package com.ecommerce.payment_service.service;

import com.ecommerce.payment_service.dto.PaymentRequestDTO;
import com.ecommerce.payment_service.dto.PaymentResponseDTO;
import com.ecommerce.payment_service.entity.Payment;
import com.ecommerce.payment_service.mapper.PaymentMapper;
import com.ecommerce.payment_service.repository.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;

    public PaymentResponseDTO savePaymentTransaction(PaymentRequestDTO dto){
        // Map DTO to Entity
        Payment transaction = new Payment();
        transaction.setOrderId(dto.getOrderId());
        transaction.setAmount(dto.getAmount());
        transaction.setCurrency(dto.getCurrency());
        transaction.setPaymentMethod(dto.getPaymentMethod());
        transaction.setStatus(dto.getStatus());

        // Set timestamps (optional if using @PrePersist)
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setUpdatedAt(LocalDateTime.now());

        // Save the transaction
        Payment paymentSavedDetails = paymentRepo.save(transaction);

        // Map Entity to response DTO
        return  PaymentMapper.toResponseDto(paymentSavedDetails);
    }
}
