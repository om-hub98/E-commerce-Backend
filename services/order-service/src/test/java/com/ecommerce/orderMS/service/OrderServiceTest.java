package com.ecommerce.orderMS.service;

import com.ecommerce.orderMS.dto.CustomerDTO;
import com.ecommerce.orderMS.dto.OrderItemDTO;
import com.ecommerce.orderMS.dto.OrderRequestDTO;
import com.ecommerce.orderMS.dto.OrderResponseDTO;
import com.ecommerce.orderMS.entity.Order;
import com.ecommerce.orderMS.entity.OrderItem;
import com.ecommerce.orderMS.enums.OrderStatus;
import com.ecommerce.orderMS.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CustomerServiceClient customerServiceClient;

    @Test
    void saveOrderDetails(){

        Long customerId = 101L;
        Long paymentId = 3000L;
        String productId = "123456";
        Long orderId = 123L;

        Order order = new Order();
        order.setOrderId(orderId);
        order.setCustomerId(customerId);
        order.setPaymentId(paymentId);
        order.setTotalAmount(500.0);

        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(productId);
        orderItem.setProductName("Test Product");
        orderItem.setPriceAtPurchase(250.0);
        orderItem.setQuantity(2);

        OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
        //orderRequestDTO.setOrderId(orderId);
        orderRequestDTO.setCustomerId(customerId);
        orderRequestDTO.setPaymentId(paymentId);
        orderRequestDTO.setTotalAmount(500.0);
        orderRequestDTO.setOrderStatus("PAID");

        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setCustomerId(customerId);
        orderResponseDTO.setPaymentId(paymentId);
        orderResponseDTO.setTotalAmount(500.0);

        OrderItemDTO itemDTO = new OrderItemDTO();
        itemDTO.setProductId(productId);
        itemDTO.setProductName("Test Product");
        itemDTO.setPriceAtPurchase(250.0);
        itemDTO.setQuantity(2);

        orderRequestDTO.setItems(List.of(itemDTO));
        orderResponseDTO.setItems(List.of((itemDTO)));
        order.setItems(List.of(orderItem));

        CustomerDTO mockCustomer = new CustomerDTO();
        mockCustomer.setCustomerId(customerId);
        mockCustomer.setFirstName("John");
        mockCustomer.setLastName("Doe");

        when(customerServiceClient.getCustomerById(customerId)).thenReturn(mockCustomer);

        // stubbing -> as real db is not here for unit testing , so stubbing is done to verify result
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        OrderResponseDTO result = orderService.saveOrderDetails(orderRequestDTO);

        Assertions.assertEquals(101L,result.getCustomerId());
        Assertions.assertEquals(500.0,result.getTotalAmount());


    }

}
