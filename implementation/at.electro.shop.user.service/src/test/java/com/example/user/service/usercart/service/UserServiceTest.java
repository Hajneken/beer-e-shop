package com.example.user.service.usercart.service;

import com.example.user.service.api.model.user.UserDto;
import com.example.user.service.usercart.dao.UserEntityDao;
import com.example.user.service.usercart.exceptionHandler.UserNotFoundException;
import com.example.user.service.usercart.model.cart.Cart;
import com.example.user.service.usercart.model.cart.Product;
import com.example.user.service.usercart.model.user.Address;
import com.example.user.service.usercart.model.user.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class UserServiceTest {
    @InjectMocks
    UserService userService;

    @Mock
    UserEntityDao userDao;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    public UserEntity getUser() {
        Address address = new Address("Cumberlandstraße", "111", 7, "1. Kellergeschoss", "vienna", 1140, "AT");
        List<Product> pidList = new ArrayList<>();
        pidList.add(new Product("321f737d-f8dd-4c9d-aca8-b8c6164a1dd1"));
        pidList.add(new Product("321f737d-f8dd-4c9d-aca8-b8c6164a1dd2"));
        pidList.add(new Product("321f737d-f8dd-4c9d-aca8-b8c6164a1dd3"));
        Cart cart = new Cart(pidList);
        UserEntity user = new UserEntity("321f737d-f8dd-4c9d-aca8-b8c6164a1dd1", "Himal", "Puri", address, cart);

        return user;
    }

    @Test
    void shouldGetCorrectUserName() throws UserNotFoundException {

        UserEntity user = getUser();
        when(userDao.findById(anyString())).thenReturn(java.util.Optional.of(user));

        UserDto userDto = userService.getUser("abcdefht");

        assertNotNull(userDto);
        assertEquals("Himal", userDto.getFirstName());
    }

    @Test
    void shouldGetListOfUsers() {
        UserEntity user = getUser();
        List<UserEntity> users = new ArrayList<>();
        users.add(user);
        when(userDao.findAll()).thenReturn(users);

        List<UserDto> usersDto = userService.getUsers();

        assertEquals(1, usersDto.size());
    }


    @Test
    public void shouldSaveProduct() throws UserNotFoundException {
        Product product = new Product("abc");
        UserEntity user = getUser();
        int previousProductSize = user.getCart().getProducts().size();
        user.getCart().addProduct(product);

        when(userDao.findById(anyString())).thenReturn(java.util.Optional.of(user));

        UserDto userDto = userService.getUser("abcdefht");
        int newProductSize = userDto.getCart().getProducts().size();
        assertEquals(newProductSize, previousProductSize + 1);
    }

    @Test
    public void shouldRemoveProduct() throws UserNotFoundException {
        Product product = new Product("321f737d-f8dd-4c9d-aca8-b8c6164a1dd1");
        UserEntity user = getUser();
        int previousProductSize = user.getCart().getProducts().size();
        user.getCart().removeProduct(product);

        when(userDao.findById(anyString())).thenReturn(java.util.Optional.of(user));

        UserDto userDto = userService.getUser("abcdefht");
        int newProductSize = userDto.getCart().getProducts().size();
        assertEquals(previousProductSize - 1, newProductSize);
    }

}