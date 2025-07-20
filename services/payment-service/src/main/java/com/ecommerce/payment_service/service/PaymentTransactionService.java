package com.ecommerce.payment_service.service;

import com.ecommerce.payment_service.dto.PaymentTransactionDTO;
import com.ecommerce.payment_service.dto.PaymentTransactionResponseDTO;
import com.ecommerce.payment_service.entity.PaymentTransaction;
import com.ecommerce.payment_service.repository.PaymentTransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentTransactionService {

    @Autowired
    private PaymentTransactionRepo paymentTransactionRepo;

    public PaymentTransactionResponseDTO recordPayment(PaymentTransactionDTO dto){
        // Map DTO to Entity
        PaymentTransaction transaction = new PaymentTransaction();
        transaction.setOrderId(dto.getOrderId());
        transaction.setCustomerId(dto.getCustomerId());
        transaction.setAmount(dto.getAmount());
        transaction.setCurrency(dto.getCurrency());
        transaction.setPaymentMethod(dto.getPaymentMethod());
        transaction.setStatus(dto.getStatus());
        transaction.setTransactionReference(dto.getTransactionReference());

        // Set timestamps (optional if using @PrePersist)
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setUpdatedAt(LocalDateTime.now());

        // Save the transaction
        PaymentTransaction saved = paymentTransactionRepo.save(transaction);

        // Map Entity to DTO
        return new PaymentTransactionResponseDTO(
                saved.getId(),
                saved.getOrderId(),
                saved.getCustomerId(),
                saved.getAmount(),
                saved.getCurrency(),
                saved.getPaymentMethod(),
                saved.getStatus(),
                saved.getTransactionReference(),
                saved.getCreatedAt(),
                saved.getUpdatedAt()
        );
    }
}
