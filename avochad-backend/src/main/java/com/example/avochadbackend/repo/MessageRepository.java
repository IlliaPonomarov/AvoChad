package com.example.avochadbackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.avochadbackend.models.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Message findByText(String text);
}
