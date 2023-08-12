package springApp.core.domain.enums;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    None,
    User,
    Admin;
    @Override
    public String getAuthority() {
        return this.toString();
    }
}
