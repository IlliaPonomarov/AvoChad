package com.example.avochadbackend.utility.validators;

import com.example.avochadbackend.models.Person;
import com.example.avochadbackend.services.PersonService;
import org.springframework.stereotype.Component;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class PersonValidator  implements Validator {

    private PersonService personService;

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

        if (this.personService.findById(person.getId()).isPresent())

    }
}
