package com.ecommerce.product.service;

import com.ecommerce.product.dto.ProductRequest;
import com.ecommerce.product.dto.ProductResponse;
import com.ecommerce.product.entity.Product;
import com.ecommerce.product.exception.ProductNotFoundException;
import com.ecommerce.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductResponse saveProduct(ProductRequest productRequest){
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());

        Product savedProduct = productRepository.save(product);
        return mapToProductResponse(savedProduct);
    }
    public List<ProductResponse> saveProducts(List<ProductRequest> productRequests) {
        List<Product> products = productRequests.stream()
                .map(this::mapToEntity)
                .collect(Collectors.toList());

        List<Product> savedProducts = productRepository.saveAll(products);

        return savedProducts.stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).collect(Collectors.toList());
    }

    public ProductResponse getByProductId(String productId){
        Optional<Product> product = Optional.ofNullable(productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product Not found.")));

        return mapToProductResponse(product.get());
    }

    public ProductResponse getByProductName(String productName){
        Optional<Product> product = Optional.ofNullable(productRepository.findByName(productName)
                .orElseThrow(() -> new ProductNotFoundException("Product Not found.")));

        return mapToProductResponse(product.get());
    }

    private ProductResponse mapToProductResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setQuantity(product.getQuantity());
        return response;
    }

    private Product mapToEntity(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());

        return product;
    }
}
