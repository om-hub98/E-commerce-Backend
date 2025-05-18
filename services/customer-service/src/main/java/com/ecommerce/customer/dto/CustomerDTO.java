package com.ecommerce.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

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
    String email;
    String phoneNumber;
    List<AddressDTO> addresses;
}
