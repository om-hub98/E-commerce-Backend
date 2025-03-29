package com.ecommerce.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

/**
 * @author omraj
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    Long addressId;
    private String street;
    private String city;
    private Integer pincode;
}
