package jy.demo.security.oauth2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import jy.demo.common.UserRole;
import jy.demo.model.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Getter
public class CustomOAuth2User implements OAuth2User {

    protected Long id;
    protected String name;
    protected String email;
    protected String socialProviderKey;

    protected Collection<? extends GrantedAuthority> authorities = new ArrayList<>();
    protected Map<String, Object> attributes = new HashMap<>();

    public User toUser() {
        return User.builder()
            .username(name)
            .email(email)
            .socialProviderKey(socialProviderKey)
            .userRole(UserRole.USER).build();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getName() {
        return name;
    }
}