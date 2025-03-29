package com.ecommerce.customer.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author omraj
 */
@EqualsAndHashCode
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class CustomerNotFoundException extends RuntimeException{

    private final String msg;
}
