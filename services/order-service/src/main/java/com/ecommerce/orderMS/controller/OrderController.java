package com.ecommerce.orderMS.controller;

import com.ecommerce.orderMS.dto.OrderRequestDTO;
import com.ecommerce.orderMS.dto.OrderResponseDTO;
import com.ecommerce.orderMS.enums.OrderStatus;
import com.ecommerce.orderMS.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> orderRequest(@RequestBody OrderRequestDTO orderRequest){
        OrderResponseDTO orderResponse = orderService.saveOrderDetails(orderRequest);
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getOrder(){
        List<OrderResponseDTO> listOfOrderResponse = orderService.getAllOrderDetails();
        return ResponseEntity.ok(listOfOrderResponse);
    }

    @GetMapping("/customers/{customerId}")
    public ResponseEntity<List<OrderResponseDTO>> getOrdersByCustomerId(    @PathVariable Long customerId){
        List<OrderResponseDTO> orders = orderService.getOrdersByCustomerId(customerId);
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<String> updateOrderStatus(@PathVariable Long orderId, @RequestParam OrderStatus status){
        String message = orderService.updateOrderStatus(orderId,status);
        return ResponseEntity.ok(message);
    }
}
