package com.ecommerce.payment_service.service.client;

import com.ecommerce.payment_service.dto.clientResponse.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", url = "http://localhost:8080/customers")
public interface CustomerClient {
    @GetMapping("/{customerId}")
    CustomerResponse getCustomerById(@PathVariable String customerId);
}