package com.ecommerce.orderMS.repository;

import com.ecommerce.orderMS.dto.OrderResponseDTO;
import com.ecommerce.orderMS.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> getOrdersByCustomerId(Long customerId);

}
