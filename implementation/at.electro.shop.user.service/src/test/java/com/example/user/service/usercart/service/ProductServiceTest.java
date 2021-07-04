package com.example.user.service.usercart.service;

import com.example.user.service.usercart.dao.ProductDao;
import com.example.user.service.usercart.exceptionHandler.ProductNotFoundException;
import com.example.user.service.usercart.model.cart.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class ProductServiceTest {

    @Mock
    ProductDao productDao;

    @InjectMocks
    ProductService productService;


    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldGetProduct() throws ProductNotFoundException {
        Product product = new Product("abc");
        when(productDao.findById(anyString())).thenReturn(java.util.Optional.of(product));

        Product returnedProduct = productService.getProduct("abc");
        assertEquals("abc", returnedProduct.getId());
    }

    @Test
    void shouldSaveProduct() {
        Product product = new Product("abc");
        when(productDao.save(product)).thenReturn(product);

        Product returnedProduct = productService.save(product);
        assertEquals("abc", returnedProduct.getId());
    }
}