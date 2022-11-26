package com.example.avochadbackend.utility.validators;

import com.example.avochadbackend.models.Person;
import com.example.avochadbackend.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class PersonValidator  implements Validator {

    private PersonService personService;

    @Autowired
    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }

    public void validate() {

    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Person person = (Person) target;

        if (this.personService.findByEmail(person.getEmail()).isPresent())
            errors.rejectValue("email", "Person with this email already exist.");

        if (this.personService.findByUsername(person.getUsername()).isPresent())
            errors.rejectValue("username", "Person with this username already exist.");


    }
}
