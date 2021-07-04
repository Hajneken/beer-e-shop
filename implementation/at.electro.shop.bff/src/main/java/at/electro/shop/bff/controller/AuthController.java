package at.electro.shop.bff.controller;

import java.security.Principal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

  @PostMapping("/securedPage")
  public String securedPage(Model model, Principal principal) {
    return "securedPage";
  }

  @GetMapping("/")
  public String index(Model model, Principal principal) {
    return "index";
  }
}
