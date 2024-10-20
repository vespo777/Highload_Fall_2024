package com.example.midterm;


import com.example.midterm.model.Product;
import com.example.midterm.repository.ProductRepository;
import com.example.midterm.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void testGetAllProducts() {
        List<Product> products = Arrays.asList(
                new Product(1L, "Product 1", 100.0),
                new Product(2L, "Product 2", 200.0)
        );

        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.getAllProducts();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void testAddProduct() {
        Product product = new Product(1L, "New Product", 100.0);

        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product result = productService.saveProduct(product);

        assertNotNull(result);
        assertEquals("New Product", result.getName());
        verify(productRepository, times(1)).save(product);
    }
}
