package com.example.user.service.usercart.service;

import com.example.user.service.api.model.cart.CartDto;
import com.example.user.service.api.model.user.UserDto;
import com.example.user.service.mapper.UserEntityMapper;
import com.example.user.service.mapper.UserEntityMapperImpl;
import com.example.user.service.usercart.dao.UserEntityDao;
import com.example.user.service.usercart.exceptionHandler.ProductNotFoundException;
import com.example.user.service.usercart.exceptionHandler.UserNotFoundException;
import com.example.user.service.usercart.model.cart.Cart;
import com.example.user.service.usercart.model.cart.Product;
import com.example.user.service.usercart.model.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserEntityDao userEntityDao;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    private UserEntityMapper userMapper = new UserEntityMapperImpl();


    public List<UserDto> getUsers() {
        List<UserDto> userDtos = new ArrayList<>();
        List<UserEntity> users = (List<UserEntity>) userEntityDao.findAll();

        for (UserEntity u : users) {
            userDtos.add(userMapper.toApi(u));
        }

        return userDtos;
    }

    public UserDto getUser(String userId) throws UserNotFoundException {
        Optional<UserEntity> opt = userEntityDao.findById(userId);
        if (opt.isEmpty()) throw new UserNotFoundException();
        return userMapper.toApi(opt.orElse(null));
    }

    public CartDto getCart(String userId) throws UserNotFoundException {
        UserDto user = getUser(userId);
        return user.getCart();
    }

    public void saveProduct(String userId, String productId) throws ProductNotFoundException {

        Product product = productService.getProduct(productId);

        if (product == null) product = new Product(productId);

        Optional<UserEntity> opt = userEntityDao.findById(userId);
        if (opt.isPresent()) {
            UserEntity user = opt.get();
            Cart cart = user.getCart();



            cart.addProduct(product);
            productService.save(product);
            user.setCart(cartService.save(cart));
            userEntityDao.save(user);
        }
    }


    public void removeProduct(String userId, String productId) throws ProductNotFoundException {
        Product product = productService.getProduct(productId);
        if (product == null) throw new ProductNotFoundException();

        Optional<UserEntity> opt = userEntityDao.findById(userId);
        if (opt.isPresent()) {
            UserEntity user = opt.get();

            Cart oldCart = user.getCart();
            oldCart.removeProduct(product);
            Cart newCart = cartService.save(oldCart);

            user.setCart(newCart);
            userEntityDao.save(user);

        }
    }
}
