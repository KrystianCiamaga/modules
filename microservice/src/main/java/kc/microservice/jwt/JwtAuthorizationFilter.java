/*
package kc.microservice.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import kc.microservice.User;
import kc.microservice.UserDetail;
import kc.microservice.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Service;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JwtAuthorizationFilter extends BasicAuthenticationFilter {



    private UserRepository userRepository;


    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository=userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {


        String header = request.getHeader(JwtProperties.HEADER);

        if(header==null || !header.startsWith(JwtProperties.TOKEN_PREFIX)){
            chain.doFilter(request,response);
            return;
        }

        Authentication authentication = getUsernamePasswordAuthentication(request);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request,response);



    }

    private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {


        String token = request.getHeader(JwtProperties.HEADER);

        if(token!=null){

            Claims claims= Jwts.parser()
                    .setSigningKey(JwtProperties.SECRET_KEY.getBytes())
                    .parseClaimsJws(token.replace(JwtProperties.TOKEN_PREFIX,""))
                    .getBody();

            String username=claims.getSubject();

            if(username!=null){


               User user = userRepository.findByLogin(username);



                UserDetail userDetails = new UserDetail(user);

                UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(
                        username,null,userDetails.getAuthorities()
                );
                return authenticationToken;

            }



        }
        return null;

    }


}
*/
