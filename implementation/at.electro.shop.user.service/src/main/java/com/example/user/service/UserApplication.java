package com.example.user.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class UserApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext context =  SpringApplication.run(UserApplication.class, args);
	}
}
