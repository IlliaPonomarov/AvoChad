package com.example.avochadbackend.security;

import java.lang.annotation.Retention;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.avochadbackend.models.User;

public class MyUserDetails implements UserDetails{

    private final User user;

    public MyUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (user.getRole().equals(0) )
            return Collections.singleton(new SimpleGrantedAuthority("ROLE_GUEST"));

        else if (user.getRole().equals(1) )
            return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));

        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }
    
    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }


    @Override
    public boolean isEnabled() {
        return false;
    }

    public User getUser() {
        return user;
    }


}
