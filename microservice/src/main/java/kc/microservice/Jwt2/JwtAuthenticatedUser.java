package kc.microservice.Jwt2;

import kc.microservice.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;


public class JwtAuthenticatedUser extends AbstractAuthenticationToken {

    private String id;
    private String login;
    private List<Role> roles;

    public JwtAuthenticatedUser(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);

    }


    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
