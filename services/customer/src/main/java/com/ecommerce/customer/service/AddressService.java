package com.ecommerce.customer.service;

import com.ecommerce.customer.dto.AddressDTO;
import com.ecommerce.customer.entity.Address;
import com.ecommerce.customer.entity.Customer;
import com.ecommerce.customer.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author omraj
 */
@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address saveAddress(AddressDTO addressdto) {
        if(addressdto != null) {
            Address address = new Address();
            address.setCity(addressdto.getCity());
            address.setStreet(addressdto.getStreet());
            address.setPincode(addressdto.getPincode());
            return addressRepository.save(address);
        }
        return null;
    }
    public Optional<Address> findExistingAddress(AddressDTO addressDTO){
        return addressRepository.findByStreetAndCityAndPincode(addressDTO.getStreet(),
                addressDTO.getCity(),addressDTO.getPincode());
    }

    public Address findOrCreateAddress(Address existingCustomerAddress, AddressDTO addressDTO) {
        if(addressDTO.getStreet().equals(existingCustomerAddress.getStreet())
                && addressDTO.getCity().equals(existingCustomerAddress.getCity())
                && addressDTO.getPincode().equals(existingCustomerAddress.getPincode())){
            return existingCustomerAddress;
        }else{
            return saveAddress(addressDTO);
        }
    }

    public static Address convertToEntity(AddressDTO addressDTO){
        Address address = new Address();
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        address.setPincode(addressDTO.getPincode());

        return address;
    }


}
