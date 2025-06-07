package com.ecommerce.customer.service;

import com.ecommerce.customer.dto.AddressDTO;
import com.ecommerce.customer.dto.CustomerDTO;
import com.ecommerce.customer.entity.Address;
import com.ecommerce.customer.entity.Customer;
import com.ecommerce.customer.exception.CustomerNotFoundException;
import com.ecommerce.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressService addressService;

    public CustomerDTO saveCustomerDetails(CustomerDTO request) {
        if (request != null) {
            Customer customer = new Customer();
            //customer.setCustomerId(request.getCustomerId());
            customer.setFirstname(request.getFirstName());
            customer.setLastname(request.getLastName());
            customer.setEmail(request.getEmail());
            customer.setPhoneNumber(request.getPhoneNumber());
            List<AddressDTO> addresssDTOList = new ArrayList<>(request.getAddresses());
            List<Address> listOfSavedAddresses = new ArrayList<>();
            if (!addresssDTOList.isEmpty()) {
                for (AddressDTO addressDTO : addresssDTOList) {
                    if(addressService.findExistingAddress(addressDTO).isEmpty()) {
                        Address savedAddress = addressService.saveAddress(addressDTO);
                        listOfSavedAddresses.add(savedAddress);
                    }
                    else{
                        listOfSavedAddresses.add(addressService.findExistingAddress(addressDTO).get());
                    }
                }
            }
            customer.setAddresses(listOfSavedAddresses);
            return convertToDTO(customerRepository.save(customer));
        }
        return null;
    }

    public CustomerDTO findCustomerById(Long customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        Customer customer = optionalCustomer.orElseThrow(() -> new CustomerNotFoundException(
                String.format("Customer not found!!")
        ));
        return convertToDTO(customer);
    }

    public List<CustomerDTO> findAllCustomers() {
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerDTO> customerDTOList = customerList
                .stream()
                .map(CustomerService::convertToDTO)
                .collect(Collectors.toList());
        return customerDTOList;
    }

    public CustomerDTO updateCustomerDetails(CustomerDTO updateCustomerDTO, Long customerId) {
        Customer existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("Customer not found!!")));

        //check address is same or not in the updated dto from the already present address
        List<Address> updatedAddresses = null;
        if(!updateCustomerDTO.getAddresses().isEmpty()){
            updatedAddresses = new ArrayList<>();
            for(AddressDTO updatedAddressDTO : updateCustomerDTO.getAddresses()) {
                updatedAddresses.add(addressService.updateAddress(existingCustomer.getAddresses(), updatedAddressDTO));
            }
        }else{
            updatedAddresses = new ArrayList<>(existingCustomer.getAddresses());
        }

        // Update customer details
        existingCustomer.setFirstname(updateCustomerDTO.getFirstName());
        existingCustomer.setLastname(updateCustomerDTO.getLastName());
        existingCustomer.setEmail(updateCustomerDTO.getEmail());
        existingCustomer.setPhoneNumber(updateCustomerDTO.getPhoneNumber());
        existingCustomer.setAddresses(updatedAddresses);

        return convertToDTO(customerRepository.save(existingCustomer));
    }

    public String deleteCustomer(Long customerId) {
        Optional<Customer> optionalCustomer = Optional.ofNullable(customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("Customer not found!!"))));
        if(optionalCustomer.isPresent()){
            customerRepository.deleteById(customerId);
            return "Delete Successfull";
        }
        return "Delete Unsuccessfull!!!!!!";
    }

    public static CustomerDTO convertToDTO(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(customer.getCustomerId());
        customerDTO.setFirstName(customer.getFirstname());
        customerDTO.setLastName(customer.getLastname());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());

        List<AddressDTO> addressDTOList = new ArrayList<>();
        for(Address address : customer.getAddresses()){
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setAddressId(address.getAddressId());
            addressDTO.setStreet(address.getStreet());
            addressDTO.setCity(address.getCity());
            addressDTO.setPincode(address.getPincode());
            addressDTOList.add(addressDTO);
        }
        customerDTO.setAddresses(addressDTOList);

        return customerDTO;
    }

    public static Customer convertToEntity(Customer customer, CustomerDTO customerDTO){

        customer.setFirstname(customerDTO.getFirstName());
        customer.setLastname(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());

        List<Address> addressList = customer.getAddresses();
        for(AddressDTO addressDTO : customerDTO.getAddresses()){
            Address address =  new Address();
            address.setAddressId(addressDTO.getAddressId());
            address.setStreet(addressDTO.getStreet());
            address.setCity(addressDTO.getCity());
            address.setPincode(addressDTO.getPincode());
            addressList.add(address);
        }
        customer.setAddresses(addressList);

        return customer;
    }
}
