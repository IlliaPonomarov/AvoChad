package com.example.avochadbackend.repo;

import com.example.avochadbackend.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findById(Long id);
    Optional<Person> findByEmail(String email);
}
