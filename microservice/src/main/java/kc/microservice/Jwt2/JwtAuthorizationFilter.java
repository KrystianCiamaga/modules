package kc.microservice.Jwt2;

import io.jsonwebtoken.*;
import kc.microservice.jwt.JwtProperties;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.ContentHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class JwtAuthorizationFilter extends OncePerRequestFilter {




    AuthenticationManager authenticationManager;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager=authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {


        String authorizationHeader = request.getHeader(JwtProperties.HEADER);



        if(authorizationHeader == null || !authorizationHeader.startsWith(JwtProperties.TOKEN_PREFIX)){
            filterChain.doFilter(request,response);
            return;
        }


            String token = authorizationHeader.replace(JwtProperties.TOKEN_PREFIX,"");

        try{


            JwtParser build = Jwts.parserBuilder()
                    .setSigningKey(JwtProperties.SECRET_KEY.getBytes())
                    .build();
            Jws<Claims> claimsJws = build.parseClaimsJws(token);

            Claims body = claimsJws.getBody();

            String username = body.getSubject();

            SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority("ROLE_CLIENT");
            List<GrantedAuthority> list = new ArrayList<>();


            list.add(simpleGrantedAuthority);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username, null, list);

            SecurityContextHolder.getContext().setAuthentication(authentication);



        }catch (JwtException e){
            System.out.println(e.getMessage()+"dupa");
        }

        filterChain.doFilter(request,response);



    }
}
