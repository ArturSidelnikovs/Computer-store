package edu.app.config.security;

import edu.app.model.user.Status;
import edu.app.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class SecurityUser implements UserDetails {
    private final User user;
    private static final Logger logger = LoggerFactory.getLogger(SecurityUser.class);

    public SecurityUser(User user) {
        this.user = user;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        this.user.getRole().getPermissions().forEach(p ->
        {
            GrantedAuthority authority = new SimpleGrantedAuthority(p.getPermission());
            authorities.add(authority);
        });
        GrantedAuthority authority2 = new SimpleGrantedAuthority("ROLE_" + user.getRole().name());
        authorities.add(authority2);
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getStatus().equals(Status.ACTIVE);
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    public User getUser() {
        return user;
    }


}