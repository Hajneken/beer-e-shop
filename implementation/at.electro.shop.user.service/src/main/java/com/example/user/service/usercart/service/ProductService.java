package com.example.user.service.usercart.service;

import com.example.user.service.usercart.dao.ProductDao;
import com.example.user.service.usercart.exceptionHandler.ProductNotFoundException;
import com.example.user.service.usercart.model.cart.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;

    public Product save(Product product) {
        return productDao.save(product);
    }

    public void removeProduct(Product product) throws ProductNotFoundException {
        if (getProduct(product.getId()) == null) throw new ProductNotFoundException();
        productDao.delete(product);
    }

    public Product getProduct(String uuid) {
        Optional<Product> opt = productDao.findById(uuid);
        if (opt.isEmpty()) return null;
        return opt.get();
    }
}
