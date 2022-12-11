package com.example.avochadbackend.services;

import com.example.avochadbackend.dto.UserRegistrationDTO;
import com.example.avochadbackend.models.User;
import com.example.avochadbackend.repo.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public Optional<User> findById(long id) {
        System.out.println("id = " + id);
        return this.userRepository.findById(id);
    }

    public Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public Optional<User> findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Transactional
    public void save(User user) {
        this.userRepository.save(user);
    }

    @Transactional
    public void update(User user) {
        this.userRepository.save(user);
    }

    @Transactional
    public void delete(User user) {
        this.userRepository.delete(user);
    }


    public User convertToPerson(UserRegistrationDTO registrationDTO) {
        return modelMapper.map(registrationDTO, User.class);
    }
}
