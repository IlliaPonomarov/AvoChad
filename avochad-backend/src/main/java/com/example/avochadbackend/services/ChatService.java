package com.example.avochadbackend.services;

import com.example.avochadbackend.models.Chat;
import com.example.avochadbackend.repo.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ChatService {

    private final ChatRepository chatRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }


    public List<Chat> findAll() {
        return this.chatRepository.findAll();
    }

    public Optional<Chat> findById(long id) {
        return this.chatRepository.findById(id);
    }

    @Transactional
    public void save(Optional<Chat> chat) {
        if (chat.isEmpty())
            return;

        this.chatRepository.save(chat.get());
    }
    @Transactional
    public void update(Optional<Chat> chat) {
        if (chat.isEmpty())
            return;

        this.chatRepository.save(chat.get());
    }



}
