package com.ecommerce.orderMS.controller;

import com.ecommerce.orderMS.dto.OrderRequestDTO;
import com.ecommerce.orderMS.dto.OrderResponseDTO;
import com.ecommerce.orderMS.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/")
    public ResponseEntity<OrderResponseDTO> orderRequest(@RequestBody OrderRequestDTO orderRequest){
        OrderResponseDTO orderResponse = orderService.placeOrder(orderRequest);
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping("/")
    public ResponseEntity<List<OrderResponseDTO>> getOrder(){
        List<OrderResponseDTO> listOfOrderResponse = orderService.getAllOrderDetails();
        return ResponseEntity.ok(listOfOrderResponse);
    }
}
