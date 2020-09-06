package Jwt2;

import io.jsonwebtoken.*;
import lombok.Getter;
import lombok.var;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import user.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class JwtAuthorizationFilter extends OncePerRequestFilter {


   private AuthenticationManager authenticationManager;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {


        String authorizationHeader = request.getHeader(JwtProperties.HEADER);


        if (authorizationHeader == null || !authorizationHeader.startsWith(JwtProperties.TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }


        String token = authorizationHeader.replace(JwtProperties.TOKEN_PREFIX, "");

        try {


            JwtParser build = Jwts.parserBuilder()
                    .setSigningKey(JwtProperties.SECRET_KEY.getBytes())
                    .build();
            Jws<Claims> claimsJws = build.parseClaimsJws(token);


            Claims body = claimsJws.getBody();

            String username = body.getSubject();
            Long id = Long.valueOf(body.get("id").toString());


            var uthorities = (List<Map<String, String>>) body.get("role");

            List<GrantedAuthority> authorities = uthorities.stream()
                    .map(m -> new SimpleGrantedAuthority(m.get("authority")))
                    .collect(Collectors.toList());


            User user = new User();
            user.setLogin(username);
            user.setId(id);

            Authentication authentication = new JwtAuthenticatedUser(user, authorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);

        } catch (JwtException e) {
            System.out.println(e.getMessage());
        }


    }
}
