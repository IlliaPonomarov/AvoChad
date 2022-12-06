package com.example.avochadbackend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.avochadbackend.models.User;
import com.example.avochadbackend.repo.UserRepository;
import com.example.avochadbackend.security.MyUserDetails;

@Service
public class UserDetailService implements UserDetailsService{

    private final UserRepository userRepository;

    @Autowired
    public UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByUsername(username);
        
        if (user.isEmpty())
            throw new UsernameNotFoundException("User not found");

        return new MyUserDetails(user.get());
    }
    
}
