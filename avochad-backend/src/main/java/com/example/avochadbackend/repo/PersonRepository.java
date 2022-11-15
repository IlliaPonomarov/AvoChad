package com.example.avochadbackend.repo;

import com.example.avochadbackend.models.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Long, Person> {
}
