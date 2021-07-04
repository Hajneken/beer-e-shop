package at.electro.shop.auth.service.users.services;

import at.electro.shop.auth.service.dao.persistence.UserRepository;
import at.electro.shop.auth.service.dao.persistence.models.AccountEntity;
import at.electro.shop.auth.service.users.services.models.UserPrincipal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceCustom implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) {
    AccountEntity user = userRepository.findByUsername(username);

    if (user == null) {
      throw new UsernameNotFoundException(username);
    }

    List<String> roles = userRepository.getRoles(username);
    List<GrantedAuthority> authorityList = new ArrayList<>();

    for (String role : roles) {
      authorityList.add(new SimpleGrantedAuthority(role));
    }

    return new UserPrincipal(user, authorityList);
  }
}
