package com.ecommerce.payment_service.service.client;

import com.ecommerce.payment_service.dto.clientResponse.OrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "order-service", url = "http://localhost:8081/orders")
public interface OrderClient {
    @GetMapping("/{orderId}")
    OrderResponse getOrderById(@PathVariable String orderId);
}