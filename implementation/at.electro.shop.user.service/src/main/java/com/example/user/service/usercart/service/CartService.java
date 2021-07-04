package com.example.user.service.usercart.service;

import com.example.user.service.usercart.dao.CartDao;
import com.example.user.service.usercart.model.cart.Cart;
import com.example.user.service.usercart.model.cart.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartDao cartDao;

    public Cart save(Cart cart) {
        return cartDao.save(cart);
    }


}
