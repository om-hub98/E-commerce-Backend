package com.ecommerce.orderMS.service.client;

import com.ecommerce.orderMS.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    public ProductDTO getProductById(String productId){
        String url = "http://localhost:8082/api/v1/product/" + productId;
        return restTemplate.getForObject(url, ProductDTO.class);
    }
}
