package com.example.avochadbackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.avochadbackend.models.Chat;

public interface ChatRepository extends JpaRepository<Chat, java.lang.Long> {
    Chat findByTitle(java.lang.String title);
}