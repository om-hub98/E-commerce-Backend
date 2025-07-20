package com.ecommerce.payment_service.controller;

import com.ecommerce.payment_service.dto.PaymentTransactionDTO;
import com.ecommerce.payment_service.dto.PaymentTransactionResponseDTO;
import com.ecommerce.payment_service.service.PaymentTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentTransactionController {

    @Autowired
    private PaymentTransactionService paymentTransactionService;

    @PostMapping
    public ResponseEntity<PaymentTransactionResponseDTO> recordPayment(@RequestBody PaymentTransactionDTO dto) {
        PaymentTransactionResponseDTO recorded = paymentTransactionService.recordPayment(dto);
        return ResponseEntity.ok(recorded);
    }
}
