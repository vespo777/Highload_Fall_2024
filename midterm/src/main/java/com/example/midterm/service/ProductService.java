package com.example.midterm.service;

import com.example.midterm.model.Product;
import com.example.midterm.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    // Cacheable products list
    @Cacheable(value = "products")
    public List<Product> getAllProducts() {

        // Simulate a delay for demonstration purposes
        try {
            Thread.sleep(2000); // Simulate a delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return productRepository.findAll();
    }

    // Cacheable single product by ID
    @Cacheable(value = "product", key = "#id")
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product addProduct(Product product) {

    }
}

