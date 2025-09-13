package com.ecommerce.orderMS.service;

import com.ecommerce.orderMS.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    public CustomerDTO getCustomerById(Long customerId){
        String url = "http://localhost:8080/api/v1/customers/" + customerId;
        return restTemplate.getForObject(url, CustomerDTO.class);
    }
}
