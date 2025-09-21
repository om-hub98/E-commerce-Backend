package com.ecommerce.payment_service.repository;

import com.ecommerce.payment_service.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepo extends JpaRepository<Payment, Long> {
    List<Payment> findByOrderId(Long orderId);
}
