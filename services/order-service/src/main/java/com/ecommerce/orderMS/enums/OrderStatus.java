package com.ecommerce.orderMS.enums;

public enum OrderStatus {
    PENDING(0),
    PAID(1),
    SHIPPED(2),
    CANCELLED(3),
    RETURNED(4);

    private final int value;

    OrderStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    // Optional: Get enum from numeric value
    public static OrderStatus fromValue(int value) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatus value: " + value);
    }
}
