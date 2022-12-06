package com.example.avochadbackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.avochadbackend.models.File;

import java.util.Optional;

public interface FileRepository extends JpaRepository<File, Long> {
    Optional<File> findById(String title);
}