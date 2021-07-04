package com.example.user.service.usercart.service;

import com.example.user.service.usercart.dao.CartDao;
import com.example.user.service.usercart.dao.ProductDao;
import com.example.user.service.usercart.exceptionHandler.ProductNotFoundException;
import com.example.user.service.usercart.model.cart.Cart;
import com.example.user.service.usercart.model.cart.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CartServiceTest {
    @Mock
    CartDao cartDao;

    @InjectMocks
    CartService cartService;


    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldGetProduct() {
        List<Product> pidList = new ArrayList<>();
        pidList.add(new Product("321f737d-f8dd-4c9d-aca8-b8c6164a1dd1"));
        pidList.add(new Product("321f737d-f8dd-4c9d-aca8-b8c6164a1dd2"));
        pidList.add(new Product("321f737d-f8dd-4c9d-aca8-b8c6164a1dd3"));
        Cart cart = new Cart(pidList);

        when(cartDao.save(cart)).thenReturn(cart);

        Cart c = cartService.save(cart);
        assertEquals(cart.getProducts().size(), c.getProducts().size());
    }
}