package com.example.user.service.usercart.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.BAD_REQUEST, reason="User not found")
public class UserNotFoundException extends Exception{
}
