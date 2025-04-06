package com.ecommerce.product.controller;

import com.ecommerce.product.dto.ProductRequest;
import com.ecommerce.product.dto.ProductResponse;
import com.ecommerce.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> saveProduct(@RequestBody ProductRequest productRequest){
        ProductResponse productResponse = productService.saveProduct(productRequest);
        return ResponseEntity.ok(productResponse);
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<ProductResponse>> saveProducts(@RequestBody List<ProductRequest> productRequest){
        List<ProductResponse> productResponses = productService.saveProducts(productRequest);
        return ResponseEntity.ok(productResponses);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        List<ProductResponse> listofProducts = productService.getAllProducts();
        return ResponseEntity.ok(listofProducts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getByProductId(@PathVariable String id){
        ProductResponse product = productService.getByProductId(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/productName/{name}")
    public ResponseEntity<ProductResponse> getByProductName(@PathVariable String name){
        ProductResponse product = productService.getByProductName(name);
        return ResponseEntity.ok(product);
    }
}
