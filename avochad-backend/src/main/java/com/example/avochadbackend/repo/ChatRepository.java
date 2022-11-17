package com.example.avochadbackend.repo;

import com.example.avochadbackend.models.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
