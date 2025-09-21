package com.ecommerce.payment_service.controller;

import com.ecommerce.payment_service.dto.PaymentRequestDTO;
import com.ecommerce.payment_service.dto.PaymentResponseDTO;
import com.ecommerce.payment_service.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponseDTO> savePayment(@RequestBody PaymentRequestDTO dto) {
        PaymentResponseDTO recorded = paymentService.savePaymentTransaction(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(recorded);
    }
}
