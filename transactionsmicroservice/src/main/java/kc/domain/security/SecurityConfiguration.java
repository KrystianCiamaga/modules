package kc.domain.security;

import Jwt2.JwtAuthorizationFilter;
import Jwt2.JwtUsernameAndPasswordAuthenticationFilter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import user.Role;
import user.UserPermissions;


@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
@NoArgsConstructor
@ComponentScan(basePackages = "Jwt2")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests().and()
                .authorizeRequests().antMatchers("assets/test").hasAuthority(UserPermissions.WRITE_READ.getPermission())
        .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager()))
                .addFilterAfter(new JwtAuthorizationFilter(authenticationManager()), JwtUsernameAndPasswordAuthenticationFilter.class);

    }

}






