package com.example.avochadbackend.controllers;

import com.example.avochadbackend.models.User;
import com.example.avochadbackend.services.UserService;
import com.example.avochadbackend.utility.exception.ErrorResponse;
import com.example.avochadbackend.utility.exception.userExceptions.UserNotCreatedException;
import com.example.avochadbackend.utility.exception.userExceptions.UserNotFoundException;
import com.example.avochadbackend.utility.exception.userExceptions.UserNotUpdatedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;
import javax.validation.Valid;
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
    public List<User> findAll() {
        System.out.println("index");
        return userService.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public Optional<User> findUserById(@PathVariable long id) {
        Optional<User> user = userService.findById(id);

        if(user.isEmpty())
            throw new UserNotFoundException("User with id " + id + " not found");

        return userService.findById(id);
    }

    // remove user by id
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeUserById(@PathVariable long id) {
        Optional<User> user = userService.findById(id);

        if(user.isEmpty())
            throw new UserNotFoundException("User with id " + id + " not found");

        userService.delete(user.get());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable long id, @RequestBody @Valid User user, BindingResult bindingResult) {
        Optional<User> userOld = userService.findById(id);

        if(bindingResult.hasErrors() || userOld.isEmpty()){
            List<FieldError> errors = bindingResult.getFieldErrors();
            StringBuilder errorMessage = new StringBuilder();

            for (FieldError error : errors)
                errorMessage.append(error.getField() + " - " + error.getDefaultMessage() + ";");
            
                throw new UserNotUpdatedException(errorMessage.toString());
        }
        userService.update(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<User> create(@RequestBody @Valid User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            List<FieldError> errors = bindingResult.getFieldErrors();
            StringBuilder errorMessage = new StringBuilder();

            for (FieldError error : errors)
                errorMessage.append(error.getField() + " - " + error.getDefaultMessage() + ";");
            
                throw new UserNotCreatedException(errorMessage.toString());
        }
        userService.save(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }



    @ExceptionHandler(UserNotUpdatedException.class)
    public ResponseEntity<ErrorResponse> handleUserNotUpdatedException(UserNotUpdatedException ex) {
        return new ResponseEntity<ErrorResponse>(new ErrorResponse(ex.getMessage(), System.currentTimeMillis()), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<ErrorResponse>(new ErrorResponse(ex.getMessage(), System.currentTimeMillis()), HttpStatus.BAD_REQUEST);
    } 
}
