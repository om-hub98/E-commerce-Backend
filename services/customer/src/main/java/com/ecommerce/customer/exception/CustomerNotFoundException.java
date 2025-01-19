package com.ecommerce.customer.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author omraj
 */
@EqualsAndHashCode
@Data
public class CustomerNotFoundException extends RuntimeException{

    private final String msg;
}
