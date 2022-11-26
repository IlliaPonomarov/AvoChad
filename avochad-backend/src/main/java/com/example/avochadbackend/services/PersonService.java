package com.example.avochadbackend.services;

import com.example.avochadbackend.models.Person;
import com.example.avochadbackend.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        return this.personRepository.findAll();
    }

    public Optional<Person> findById(long id) {
        return this.personRepository.findById(id);
    }

    public Optional<Person> findByEmail(String email) {
        return this.personRepository.findByEmail(email);
    }

    public Optional<Person> findByUsername(String username) {
        return this.personRepository.findByUsername(username);
    }

    @Transactional
    public void save(Person person1) {
        this.personRepository.save(person1);
    }


}
