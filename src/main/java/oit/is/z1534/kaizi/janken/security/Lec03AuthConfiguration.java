package oit.is.z1534.kaizi.janken.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class Lec03AuthConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    //pAssw0rd
    auth.inMemoryAuthentication().withUser("user1").password("$2y$10$rJ9yqGht2W96MdIJICRQQOuUiYrt2eDokKnDuZZof2DPs83PN6QdC").roles("USER");

    //password
    auth.inMemoryAuthentication().withUser("user2").password("$2y$10$gFjHRr7X56Tm8vigd8.6rOdHwRasTa.MJAnUd3.vP51weegcR6.Ay").roles("USER");
    auth.inMemoryAuthentication().withUser("しみず").password("$2y$10$gFjHRr7X56Tm8vigd8.6rOdHwRasTa.MJAnUd3.vP51weegcR6.Ay").roles("USER");
    auth.inMemoryAuthentication().withUser("履歴書").password("$2y$10$gFjHRr7X56Tm8vigd8.6rOdHwRasTa.MJAnUd3.vP51weegcR6.Ay").roles("USER");

  }

    @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.formLogin();

    http.authorizeRequests().antMatchers("/lec02/**").authenticated();

    http.csrf().disable();
    http.headers().frameOptions().disable();

     http.logout().logoutSuccessUrl("/");
  }
}
