package com.ecommerce.orderMS.mapper;

import com.ecommerce.orderMS.dto.OrderItemDTO;
import com.ecommerce.orderMS.dto.OrderResponseDTO;
import com.ecommerce.orderMS.entity.Order;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderResponseDTO mapToOrderResponseDTO(Order order){
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setOrderId(order.getOrderId());
        orderResponseDTO.setCustomerId(order.getCustomerId());
        orderResponseDTO.setTotalAmount(order.getTotalAmount());
        orderResponseDTO.setPaymentId(order.getPaymentId());
        orderResponseDTO.setOrderStatus(Optional.ofNullable(order.getOrderStatus())
                .map(Enum::name)
                .orElse(null));
        orderResponseDTO.setCreatedAt(order.getCreatedAt());

        List<OrderItemDTO> itemDTOs = order.getItems().stream().map(item -> {
            OrderItemDTO itemDTO = new OrderItemDTO();
            itemDTO.setProductId(item.getProductId());
            itemDTO.setProductName(item.getProductName());
            itemDTO.setPriceAtPurchase(item.getPriceAtPurchase());
            itemDTO.setQuantity(item.getQuantity());
            return itemDTO;
        }).collect(Collectors.toList());

        orderResponseDTO.setItems(itemDTOs);
        return orderResponseDTO;
    }
}
