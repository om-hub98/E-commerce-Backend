package com.ecommerce.orderMS.service;

import com.ecommerce.orderMS.dto.*;
import com.ecommerce.orderMS.entity.Order;
import com.ecommerce.orderMS.entity.OrderItem;
import com.ecommerce.orderMS.enums.OrderStatus;
import com.ecommerce.orderMS.exception.InsufficentInventoryException;
import com.ecommerce.orderMS.mapper.OrderMapper;
import com.ecommerce.orderMS.repository.OrderItemRepository;
import com.ecommerce.orderMS.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CustomerServiceClient customerServiceClient;

    @Autowired
    private ProductServiceClient productServiceClient;


    public OrderResponseDTO saveOrderDetails(OrderRequestDTO orderRequest){

        CustomerDTO customer = customerServiceClient.getCustomerById(orderRequest.getCustomerId());
        if(customer == null){
            throw new RuntimeException("No Customer found with ID :"+customer.getCustomerId());
        }
        Order order = new Order();
        order.setCustomerId(orderRequest.getCustomerId());
        order.setTotalAmount(orderRequest.getTotalAmount());
        order.setPaymentId(orderRequest.getPaymentId());
        // Convert String to OrderStatus enum
        if (orderRequest.getOrderStatus() != null) {
            try {
                OrderStatus statusEnum = OrderStatus.valueOf(orderRequest.getOrderStatus());
                order.setOrderStatus(statusEnum);
            } catch (IllegalArgumentException e) {
                // Handle invalid status string
                throw new RuntimeException("Invalid order status: " + orderRequest.getOrderStatus());
            }
        } else {
            order.setOrderStatus(OrderStatus.PENDING);  // default
        }
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        List<OrderItem> listOfOrderItems = orderRequest.getItems().stream().map(dto -> {
            OrderItem item = new OrderItem();
            ProductDTO productDTO = productServiceClient.getProductById(dto.getProductId());
            item.setProductId(dto.getProductId());
            item.setProductName(dto.getProductName());
            item.setPriceAtPurchase(dto.getPriceAtPurchase());
            if(dto.getQuantity() <= productDTO.getQuantity()){
                item.setQuantity(dto.getQuantity());
            }else {
                throw new InsufficentInventoryException("Quantity not available : "+productDTO.getName());
            }

            item.setOrder(order); // Set the back-reference
            return item;
        }).collect(Collectors.toList());
        order.setItems(listOfOrderItems);
        return OrderMapper.mapToOrderResponseDTO(orderRepository.save(order));
    }

    public OrderResponseDTO getOrderById(Long orderId){
        Order order = orderRepository.getById(orderId);
        OrderResponseDTO orderResponse = OrderMapper.mapToOrderResponseDTO(order);
        return orderResponse;
    }

    public List<OrderResponseDTO> getAllOrderDetails(){
        List<Order> listOfOrders = orderRepository.findAll();
        List<OrderResponseDTO> listOfOrderResponse = listOfOrders.stream()
                .map(OrderMapper::mapToOrderResponseDTO)
                .toList();
        return listOfOrderResponse;
    }

    public List<OrderResponseDTO> getOrdersByCustomerId(Long customerId){
        List<Order> orders = orderRepository.getOrdersByCustomerId(customerId);
        return orders.stream()
                .map(OrderMapper::mapToOrderResponseDTO)
                .toList();
    }

    public String updateOrderStatus(Long orderId, OrderStatus status){
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setOrderStatus(status);
        order.setUpdatedAt(LocalDateTime.now());
        orderRepository.save(order);
        return "Order updated successfully";
    }


}
