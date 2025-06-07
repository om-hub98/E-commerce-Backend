package com.ecommerce.orderMS.repository;

import com.ecommerce.orderMS.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
