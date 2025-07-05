package com.ecommerce.orderMS.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class InsufficentInventoryException extends RuntimeException{

    private final String msg;
}
