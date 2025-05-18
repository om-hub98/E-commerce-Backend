package com.ecommerce.orderMS.repository;

import com.ecommerce.orderMS.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
