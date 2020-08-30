package kc.microservice.Jwt2;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import kc.microservice.UserDetail;
import kc.microservice.jwt.JwtProperties;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.Date;

public class JwtUsernameAndPasswordAuthenticaionFilter extends UsernamePasswordAuthenticationFilter {



    private AuthenticationManager authenticationManager;

    public JwtUsernameAndPasswordAuthenticaionFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager=authenticationManager;
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {


        LoginViewModel usernameAndPassword = new ObjectMapper().readValue(request.getInputStream(), LoginViewModel.class);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                usernameAndPassword.getUsername(),usernameAndPassword.getPassword(),new ArrayList<>());


        return authenticationManager.authenticate(usernamePasswordAuthenticationToken);

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {

        UserDetail userDetail = (UserDetail)authResult.getPrincipal();

        String token = Jwts.builder()
                .setSubject(userDetail.getUsername())
                .claim("role",authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ JwtProperties.EXPIRATION_DATE))
                .signWith(Keys.hmacShaKeyFor(JwtProperties.SECRET_KEY.getBytes()))
                .compact();

        response.addHeader("Authorization",JwtProperties.TOKEN_PREFIX+token);

    }
}
