package com.example.avochadbackend.controllers;

import com.example.avochadbackend.models.Person;
import com.example.avochadbackend.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.Optional;
import java.util.List;


@Controller
public class TestGraphQlController {

    private final PersonService personService;

    @Autowired
    public TestGraphQlController(PersonService personService) {
        this.personService = personService;
    }

    @QueryMapping
    public List<Person> findAllPersons() {
        return this.personService.findAll();
    }
    @QueryMapping
    public Person getPersonById(@Argument Long id){
        //     public Person(String firstName, String secondName, int age, String username, String email, String password, LanguageCode languageCode, Status status) {
        Optional<Person> person = this.personService.findById(id);
//        Person person1 = new Person("Test", "Testov", 66, "@testovac", "fffd@gmail.com", "jkhjkhkjhj", LanguageCode.UA, Status.OFFLINE);
        if (person.isEmpty())
            return null;


        return person.get();
    }


    public void savePerson() {

    }

}
