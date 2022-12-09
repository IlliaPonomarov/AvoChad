package com.example.avochadbackend.utility.validators;

import com.example.avochadbackend.models.User;
import com.example.avochadbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class UserValidator  implements Validator {

    private UserService personService;

    @Autowired
    public UserValidator(UserService personService) {
        this.personService = personService;
    }

    public void validate() {

    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        User person = (User) target;

        if (this.personService.findByEmail(person.getEmail()).isPresent())
            errors.rejectValue("email", "Person with this email already exist.");

        if (this.personService.findByUsername(person.getUsername()).isPresent())
            errors.rejectValue("username", "Person with this username already exist.");
    }
}
