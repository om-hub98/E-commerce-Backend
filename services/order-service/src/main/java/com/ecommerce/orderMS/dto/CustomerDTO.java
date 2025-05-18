package com.ecommerce.orderMS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author omraj pradhan
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    Long customerId;
    String firstName;
    String lastName;
}
