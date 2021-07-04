package com.example.user.service.usercart.dao;

import com.example.user.service.usercart.model.cart.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends CrudRepository<Product, String> {
}
