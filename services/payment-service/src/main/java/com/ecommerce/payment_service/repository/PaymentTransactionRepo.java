package com.ecommerce.payment_service.repository;

import com.ecommerce.payment_service.entity.PaymentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentTransactionRepo extends JpaRepository<PaymentTransaction, Long> {
    List<PaymentTransaction> findByCustomerId(Long customerId);
    List<PaymentTransaction> findByOrderId(Long orderId);
}
