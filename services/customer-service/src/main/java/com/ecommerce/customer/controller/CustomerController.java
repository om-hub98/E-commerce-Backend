package com.ecommerce.customer.controller;

import com.ecommerce.customer.dto.CustomerDTO;
import com.ecommerce.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/")
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customer) {
        CustomerDTO customerDTO = customerService.saveCustomerDetails(customer);
        return ResponseEntity.ok(customerDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        CustomerDTO customerdto = customerService.findCustomerById(id);
        if (customerdto != null) {
            return ResponseEntity.ok(customerdto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> listOfCustomers = customerService.findAllCustomers();
        return ResponseEntity.ok(listOfCustomers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomerDetails(@RequestBody CustomerDTO customerdto, @PathVariable Long id){
        CustomerDTO customerDTOResult = customerService.updateCustomerDetails(customerdto,id);
        return ResponseEntity.ok(customerDTOResult);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id){
        String message = customerService.deleteCustomer(id);
        return ResponseEntity.ok(message);
    }
}

