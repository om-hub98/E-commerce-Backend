package com.ecommerce.orderMS.mapper;

import com.ecommerce.orderMS.dto.OrderItemDTO;
import com.ecommerce.orderMS.dto.OrderResponseDTO;
import com.ecommerce.orderMS.entity.Order;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderResponseDTO mapToOrderResponseDTO(Order order){
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setOrderId(order.getOrderId());
        orderResponseDTO.setCustomerId(order.getCustomerId());
        orderResponseDTO.setTotalAmount(order.getTotalAmount());
        orderResponseDTO.setOrderStatus(order.getStatus().name());
        orderResponseDTO.setCreatedAt(order.getCreatedAt());

        List<OrderItemDTO> itemDTOs = order.getItems().stream().map(item -> {
            OrderItemDTO i = new OrderItemDTO();
            i.setProductId(item.getProductId());
            i.setProductName(item.getProductName());
            i.setPriceAtPurchase(item.getPriceAtPurchase());
            i.setQuantity(item.getQuantity());
            return i;
        }).collect(Collectors.toList());

        orderResponseDTO.setItems(itemDTOs);
        return orderResponseDTO;
    }
}
