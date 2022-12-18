package com.example.avochadbackend.controllers;

import com.example.avochadbackend.models.User;
import com.example.avochadbackend.services.UserService;
import com.example.avochadbackend.utility.exception.UserNotCreatedException;
import com.example.avochadbackend.utility.exception.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<User> index() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> show(long id) {
        Optional<User> user = userService.findById(id);

        if(user.isEmpty())
            throw new UserNotFoundException("User with id " + id + " not found");


        return userService.findById(id);
    }

    @PostMapping("/")
    public User create(User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            StringBuilder messageErrors = new StringBuilder();
            for(FieldError fieldError : bindingResult.getFieldErrors()) 
                messageErrors.append(fieldError.getDefaultMessage()).append(";");
            
            throw new UserNotCreatedException(messageErrors.toString());
        }
        
        userService.save(user);

        return user;
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(long id, User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            StringBuilder messageErrors = new StringBuilder();
            for(FieldError fieldError : bindingResult.getFieldErrors()) 
                messageErrors.append(fieldError.getDefaultMessage()).append(";");
            
            throw new UserNotCreatedException(messageErrors.toString());
        }

        Optional<User> userToUpdate = userService.findById(id);

        if(userToUpdate.isEmpty())
            throw new UserNotFoundException("User with id " + id + " not found");

        userService.save(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(long id) {
        Optional<User> user = userService.findById(id);

        if(user.isEmpty())
            throw new UserNotFoundException("User with id " + id + " not found");

        userService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
