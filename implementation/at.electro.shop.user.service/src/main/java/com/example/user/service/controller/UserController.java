package com.example.user.service.controller;

import com.example.user.service.api.model.cart.CartDto;
import com.example.user.service.api.model.user.UserDto;
import com.example.user.service.usercart.exceptionHandler.ProductNotFoundException;
import com.example.user.service.usercart.exceptionHandler.UserNotFoundException;
import com.example.user.service.usercart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<UserDto> getUsers() {
        List<UserDto> users = userService.getUsers();

        if (users != null) return users;
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
    }


    @GetMapping("/api/v1/users/{userId}")
    @ResponseBody
    public UserDto getUser(@PathVariable String userId) {
        try {
            return userService.getUser(userId);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found", e);
        }

    }


    @GetMapping("/api/v1/users/{userId}/cart")
    @ResponseBody
    public CartDto getCart(@PathVariable String userId) {
        try {
            return userService.getCart(userId);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found", e);
        }
    }


    @PostMapping("/api/v1/users/{userId}/cart/product/{productId}")
    @ResponseBody
    public CartDto saveProduct(@PathVariable String userId, @PathVariable String productId) {
        try {
            userService.saveProduct(userId, productId);
            return userService.getCart(userId);
        } catch (UserNotFoundException | ProductNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found", e);
        }
    }


    @DeleteMapping("/api/v1/users/{userId}/cart/product/{productId}")
    @ResponseBody
    public CartDto removeProduct(@PathVariable String userId, @PathVariable String productId) {
        try {
            userService.removeProduct(userId, productId);
            return userService.getCart(userId);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found", e);
        } catch (ProductNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found", e);
        }
    }

}
