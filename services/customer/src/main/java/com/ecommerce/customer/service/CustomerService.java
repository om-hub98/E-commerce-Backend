package com.ecommerce.customer.service;

import com.ecommerce.customer.entity.Customer;
import com.ecommerce.customer.exception.CustomerNotFoundException;
import com.ecommerce.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements CustomerServiceInterface {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public String createCustomerAccount(Customer customer) {
        if (customer != null) {
            customerRepository.save(customer);
            return "Saved Successfully";
        }
        return "Customer is null";
    }

    @Override
    public Customer findCustomerById(Long customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        return optionalCustomer.orElseThrow(() -> new CustomerNotFoundException(
            String.format("Customer not found!!")
        ));
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    //@Override
//    public void updateCustomerDetails(Customer obj) {
//        Optional<Customer> optionalCustomer = Optional.ofNullable(customerRepository.findById(obj.getCustomerId())
//                .orElseThrow(() -> new CustomerNotFoundException(String.format("Customer not found!!"))));
//        if(optionalCustomer.isPresent()){
//            Customer customer = new Customer();
//            customer.setFirstname(obj.getFirstname());
//            customer.setLastname(obj.getLastname());
//            customer.setEmail(obj.getEmail());
//            customer.setPhoneNumber(obj.getPhoneNumber());
//
//            Address address = new Address();
//            address.setCity(obj.getAddress().getCity());
//            address.setStreet(obj.getAddress().getStreet());
//            address.setPincode(obj.getAddress().getPincode());
//            customer.setAddress(address);
//
//            customerRepository.save(customer);
//        }
//    }

//    @Override
//    public void deleteCustomer(Long customerId) {
//        customerRepository.deleteById(customerId);
//    }
}
