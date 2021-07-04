package at.electro.shop.bff.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.antMatcher("/**").authorizeRequests()
        .antMatchers("/v3/api-docs",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/webjars/**" ,
            /*Probably not needed*/ "/swagger.json").permitAll()
        .anyRequest().authenticated()
        .and()
        .csrf().disable()
        .oauth2Login();
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/v3/api-docs/**");
    web.ignoring().antMatchers("/swagger.json");
    web.ignoring().antMatchers("/swagger-ui.html");
    web.ignoring().antMatchers("/swagger-resources/**");
    web.ignoring().antMatchers("/webjars/**");
  }
}
