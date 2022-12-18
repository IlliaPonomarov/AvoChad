package com.example.avochadbackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.avochadbackend.models.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(long id);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    void deleteById(long id);
}
