package Jwt2;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import user.User;

import java.util.List;


@ComponentScan(basePackages = "kc.microservice")
public class JwtAuthenticatedUser extends AbstractAuthenticationToken {

    private User user;


    public JwtAuthenticatedUser(User user, List<GrantedAuthority> list) {

        super(list);
        this.user = user;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }
}
