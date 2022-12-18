package com.example.avochadbackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.example.avochadbackend.models.Chat;
import com.example.avochadbackend.models.User;

public interface ChatRepository extends JpaRepository<Chat, java.lang.Long> {
    Chat findByTitle(java.lang.String title);
    List<Chat> findByUsers(User user);
    List<Chat> findByUsersId(long id);
}