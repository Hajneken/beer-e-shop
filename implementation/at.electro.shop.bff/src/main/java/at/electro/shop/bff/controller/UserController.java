package at.electro.shop.bff.controller;

import at.electro.shop.bff.api.user.models.cart.CartDto;
import at.electro.shop.bff.api.user.models.user.UserDto;
import at.electro.shop.bff.services.UserService;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/user/me", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getCurrentUser(Authentication authentication) {
        return ResponseEntity.ok(authentication.getPrincipal());
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<UserDto> getUsers() {
        List<UserDto> users = userService.getUsers();
        return users;
    }

    @GetMapping("/users/{userId}")
    @ResponseBody
    public UserDto getUser(@PathVariable String userId) {
        return userService.getUser(userId);
    }

    @GetMapping("/users/{userId}/cart")
    @ResponseBody
    public CartDto getCart(@PathVariable String userId) {
        return userService.getCart(userId);

    }

    @PostMapping("/api/v1/users/{userId}/cart/product/{productId}")
    @ResponseBody
    public CartDto saveProduct(@PathVariable String userId, @PathVariable String productId) {
        userService.saveProduct(userId, productId);
        return userService.getCart(userId);

    }

    @DeleteMapping("/api/v1/users/{userId}/cart/product/{productId}")
    @ResponseBody
    public CartDto removeProduct(@PathVariable String userId, @PathVariable String productId) {
        userService.removeProduct(userId, productId);
        return userService.getCart(userId);
    }
}