package com.ecommerce.orderMS.service;

import com.ecommerce.orderMS.dto.CustomerDTO;
import com.ecommerce.orderMS.dto.OrderItemDTO;
import com.ecommerce.orderMS.dto.OrderRequestDTO;
import com.ecommerce.orderMS.dto.OrderResponseDTO;
import com.ecommerce.orderMS.entity.Order;
import com.ecommerce.orderMS.entity.OrderItem;
import com.ecommerce.orderMS.enums.OrderStatus;
import com.ecommerce.orderMS.mapper.OrderMapper;
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
    private CustomerServiceClient customerServiceClient;


    public OrderResponseDTO placeOrder(OrderRequestDTO orderRequest){
        System.out.println("Order request : "+orderRequest);
        CustomerDTO customer = customerServiceClient.getCustomerById(orderRequest.getCustomerId());
        if(customer == null){
            throw new RuntimeException("No Customer found with ID :"+customer.getCustomerId());
        }

        Order order = new Order();
        order.setCustomerId(orderRequest.getCustomerId());
        order.setTotalAmount(orderRequest.getTotalAmount());
        order.setPaymentId(orderRequest.getPaymentId());
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        List<OrderItem> listOfOrderItems = orderRequest.getItems().stream().map(orderItemDTO -> {
            OrderItem item = new OrderItem();
            item.setProductId(orderItemDTO.getProductId());
            item.setProductId(orderItemDTO.getProductId());
            item.setProductName(orderItemDTO.getProductName());
            item.setPriceAtPurchase(orderItemDTO.getPriceAtPurchase());
            item.setQuantity(orderItemDTO.getQuantity());
            return item;
        }).collect(Collectors.toList());

        order.setItems(listOfOrderItems);

        return OrderMapper.mapToOrderResponseDTO(orderRepository.save(order));

    }

    public List<OrderResponseDTO> getAllOrderDetails(){
        List<Order> listOfOrders = orderRepository.findAll();

        List<OrderResponseDTO> listOfOrderResponse = listOfOrders.stream()
                .map(OrderMapper::mapToOrderResponseDTO)
                .toList();

        return listOfOrderResponse;
    }


}
