package com.example.user.service.usercart.exceptionHandler;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.BAD_REQUEST, reason="Product not found")
public class ProductNotFoundException extends Exception{
}
