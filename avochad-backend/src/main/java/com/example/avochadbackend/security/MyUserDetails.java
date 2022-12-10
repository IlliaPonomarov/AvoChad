package com.example.avochadbackend.security;

import java.lang.annotation.Retention;
import java.util.Collection;
import java.util.Collections;

import com.example.avochadbackend.utility.enums.Role;
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
        if (user.getRole().equals(Role.GUEST) )
            return Collections.singleton(new SimpleGrantedAuthority("ROLE_GUEST"));

        else if (user.getRole().equals(Role.USER) )
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
        return true;
    }

    public User getUser() {
        return this.user;
    }


}
