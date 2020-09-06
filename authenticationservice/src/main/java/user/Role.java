package user;

import org.elasticsearch.common.util.set.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {

    DEVELOPER(Sets.newHashSet(UserPermissions.WRITE_READ)),
    CLIENT(Sets.newHashSet(UserPermissions.READ)),
    SYSTEM(Sets.newHashSet(UserPermissions.WRITE_READ));

    private Set<UserPermissions> permissions;


    Role(Set<UserPermissions> permissions) {
        this.permissions=permissions;
    }

    public Set<UserPermissions> getPermissions() {
        return permissions;
    }


    public Set<SimpleGrantedAuthority> simpleGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());

        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
