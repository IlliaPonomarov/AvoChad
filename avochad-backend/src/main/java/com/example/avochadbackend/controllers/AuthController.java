package com.example.avochadbackend.controllers;

import javax.validation.Valid;

import com.example.avochadbackend.dto.UserRegistrationDTO;
import com.example.avochadbackend.models.User;
import com.example.avochadbackend.utility.exception.UserNotCreatedException;
import com.example.avochadbackend.utility.exception.UserNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.avochadbackend.dto.AuthenticationDTO;
import com.example.avochadbackend.security.JWTUtil;
import com.example.avochadbackend.services.RegistrationService;
import com.example.avochadbackend.services.UserService;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private final UserService userService;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final RegistrationService registrationService;

    @Autowired
    public AuthController(UserService userService, JWTUtil jwtUtil, RegistrationService registrationService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.registrationService = registrationService;
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid AuthenticationDTO authenticationDTO, BindingResult bindingResult) {
        System.out.println(authenticationDTO.toString());
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(authenticationDTO.getUsername(), authenticationDTO.getPassword());
        String jwtToken = "";
        Optional<User> authUser = userService.findByUsername(authenticationDTO.getUsername());

        if (bindingResult.hasErrors() || authUser.isEmpty()) {
            StringBuilder messageErrors = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            for (FieldError fieldError : fieldErrors)
                messageErrors.append(fieldError.getField())
                        .append(" - ")
                        .append(fieldError.getDefaultMessage())
                        .append(";");

            throw new UserNotExistException(messageErrors.toString());
        }

        try {
            authenticationManager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);    
        } catch (BadCredentialsException exception) {
            throw new BadCredentialsException("Incorrect username or password.");
        }

        System.out.println(SecurityContextHolder.getContext().getAuthentication().isAuthenticated());

        jwtToken = jwtUtil.generateToken(authenticationDTO.getUsername());
        return new ResponseEntity<>(jwtToken, HttpStatus.OK);
    }

    @PostMapping("/registration")
    public ResponseEntity<String> register(@RequestBody @Valid UserRegistrationDTO registrationDTO, BindingResult bindingResult) {
        User user = userService.convertToPerson(registrationDTO);
        String token = "";

        if (bindingResult.hasErrors()) {
            StringBuilder messageErrors = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            for (FieldError fieldError : fieldErrors)
                messageErrors.append(fieldError.getField())
                        .append(" - ")
                        .append(fieldError.getDefaultMessage())
                        .append(";");

            throw new UserNotCreatedException(messageErrors.toString());
        }

        token = jwtUtil.generateToken(user.getUsername());
        registrationService.registerUser(user);

        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @ExceptionHandler(UserNotExistException.class)
    public ResponseEntity<String> handleUserNotExistException(UserNotExistException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotCreatedException.class)
    public ResponseEntity<String> handleUserNotCreatedException(UserNotCreatedException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }




}
