package com.ecommerce.customer.service;

import com.ecommerce.customer.entity.Customer;

import java.util.List;

public interface CustomerServiceInterface {
    public String createCustomerAccount(Customer obj);
   // public void updateCustomerDetails(Customer obj);
    //public void deleteCustomer(Long customerId);
    public Customer findCustomerById(Long customerId);
    public List<Customer> findAllCustomers();
}
