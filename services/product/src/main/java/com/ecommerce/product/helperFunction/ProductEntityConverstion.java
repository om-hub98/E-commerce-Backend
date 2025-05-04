package com.ecommerce.product.helperFunction;

import com.ecommerce.product.dto.ProductRequest;
import com.ecommerce.product.dto.ProductResponse;
import com.ecommerce.product.entity.Product;

public class ProductEntityConverstion {

    public ProductResponse mapToProductResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setQuantity(product.getQuantity());
        return response;
    }

    public Product mapToEntity(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());

        return product;
    }

    public Product mapToEntity(ProductResponse productResponse) {
        Product product = new Product();
        product.setName(productResponse.getName());
        product.setDescription(productResponse.getDescription());
        product.setPrice(productResponse.getPrice());
        product.setQuantity(productResponse.getQuantity());

        return product;
    }
}
