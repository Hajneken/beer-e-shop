package at.electro.shop.auth.service.controller;

import at.electro.shop.auth.service.dao.persistence.UserRepository;
import java.security.Principal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private final UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping("/user/me")
  public Principal user(Principal principal) {
    return principal;
  }

  @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<String> user(@PathVariable String username) {
    return ResponseEntity.ok(userRepository.findByUsername(username).getUuid());
  }
}