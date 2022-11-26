package com.example.avochadbackend.controllers;

import com.example.avochadbackend.models.Person;
import com.example.avochadbackend.mutations.inputs.CreatePerson;
import com.example.avochadbackend.services.PersonService;
import com.example.avochadbackend.utility.exceptions.PersonAlreadyExistException;
import com.example.avochadbackend.utility.exceptions.PersonNotCreatedException;
import com.example.avochadbackend.utility.validators.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Optional;
import java.util.List;


@Controller
public class PersonController {

    private final PersonService personService;
    private final PersonValidator personValidator;

    @Autowired
    public PersonController(PersonService personService, PersonValidator personValidator) {
        this.personService = personService;
        this.personValidator = personValidator;
    }

    @QueryMapping
    public List<Person> findAllPersons() {
        return this.personService.findAll();
    }
    @QueryMapping
    public Person getPersonById(@Argument Long id){
        Optional<Person> person = this.personService.findById(id);

        if (person.isEmpty())
            throw new PersonAlreadyExistException("Person not found", "invalidField");


        return person.get();
    }


    @MutationMapping
    public String addPerson(
            @Argument CreatePerson createPerson, BindingResult bindingResult
    ) {
//        Person person1 = new Person("Test", "Testov", 66, "@testovac", "fffd@gmail.com", "jkhjkhkjhj", LanguageCode.UA, Status.OFFLINE);

        personValidator.validate(createPerson, bindingResult);

        if (bindingResult.hasErrors()) {
            StringBuilder messageError = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            for (FieldError fieldError : fieldErrors)
                messageError.append(fieldError.getField()).append(" - ").append(fieldError.getDefaultMessage()).append(";");

            throw new PersonNotCreatedException(messageError.toString(), "invalidField");
        }
        System.out.println("Hello");
        System.out.println("Hello");
        System.out.println("Hello");
        System.out.println("Hello");

        return null;
    }

}
