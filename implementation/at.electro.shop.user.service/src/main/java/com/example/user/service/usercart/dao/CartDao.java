package com.example.user.service.usercart.dao;

import com.example.user.service.usercart.model.cart.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDao extends CrudRepository<Cart, String>{
}
