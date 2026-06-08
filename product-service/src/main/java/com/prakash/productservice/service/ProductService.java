package com.prakash.productservice.service;

import com.prakash.productservice.entity.Product;
import com.prakash.productservice.repository.ProductRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Cacheable(value = "productSearch", key = "#keyword")
    public List<Product> searchProducts(String keyword) {
        System.out.println("Fetching from DB...");
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }
}