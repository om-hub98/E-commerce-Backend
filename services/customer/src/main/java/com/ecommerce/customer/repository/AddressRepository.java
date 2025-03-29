package com.ecommerce.customer.repository;

import com.ecommerce.customer.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author omraj
 */
public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findByStreetAndCityAndPincode(String street, String city, Integer pincode);
}
