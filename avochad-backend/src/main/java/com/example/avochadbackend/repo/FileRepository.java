package com.example.avochadbackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.avochadbackend.models.File;

public interface FileRepository extends JpaRepository<File, Long> {
    File findByTitle(String title);
}