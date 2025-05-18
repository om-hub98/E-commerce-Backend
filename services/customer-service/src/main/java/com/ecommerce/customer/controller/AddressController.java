package com.ecommerce.customer.controller;

import com.ecommerce.customer.dto.AddressDTO;
import com.ecommerce.customer.entity.Address;
import com.ecommerce.customer.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author omraj
 */
@RestController
@RequestMapping("/api/addresses")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<Address> addAddress(@RequestBody AddressDTO addressdto) {
        Address optionalSavedAddress = addressService.saveAddress(addressdto);
        return ResponseEntity.ok(optionalSavedAddress);
    }
}
