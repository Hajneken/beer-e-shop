package at.electro.shop.bff.services;

import at.electro.shop.bff.api.user.models.cart.CartDto;
import at.electro.shop.bff.api.user.models.user.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "${clients.user.name}", url = "${clients.user.url}")
public interface UserService {

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/users", produces = "application/json")
    public List<UserDto> getUsers();

    @RequestMapping(method = RequestMethod.GET,value= "/api/v1/users/{userId}", produces = "application/json")
    public UserDto getUser(@PathVariable String userId);

    @RequestMapping(method = RequestMethod.GET, value= "/api/v1/users/{userId}/cart", produces = "application/json")
    public CartDto getCart(@PathVariable String userId);

    @RequestMapping(method = RequestMethod.POST, value = "/api/v1/users/{userId}/cart/product/{productId}", produces = "application/json")
    public CartDto saveProduct(@PathVariable String userId, @PathVariable String productId);

    @RequestMapping(method = RequestMethod.DELETE, value = "/api/v1/users/{userId}/cart/product/{productId}",produces = "application/json")
    public CartDto removeProduct(@PathVariable String userId, @PathVariable String productId);
}
