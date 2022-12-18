package com.example.avochadbackend.services;

import com.example.avochadbackend.dto.ChatDTO;
import com.example.avochadbackend.models.Chat;
import com.example.avochadbackend.repo.ChatRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ChatService {

    private final ChatRepository chatRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ChatService(ChatRepository chatRepository, ModelMapper modelMapper) {
        this.chatRepository = chatRepository;
        this.modelMapper = modelMapper;
    }


    public List<Chat> findAll() {
        return this.chatRepository.findAll();
    }

    public Optional<Chat> findById(long id) {
        return this.chatRepository.findById(id);
    }

    public List<Chat> findByUserId(long id) {
        return this.chatRepository.findByUsersId(id);
    }

    @Transactional
    public void save(Chat chat) {
        chat.setCreatedAt(new Date());
        chat.setUpdatedAt(new Date());

        this.chatRepository.save(chat);
    }

    @Transactional
    public void update(Chat chat, ChatDTO chatDTO) {
        chat.setTitle(chatDTO.getTitle());
        chat.setType(chatDTO.getType());
        chat.setUsers(chatDTO.getUsers()); 
        chat.setUpdatedAt(new Date());

        this.chatRepository.save(chat);
    }

    @Transactional
    public void delete(Chat chat) {
        this.chatRepository.delete(chat);
    }

    @Transactional
    public void deleteById(long id) {
        this.chatRepository.deleteById(id);
    }


    public Chat convertToEntity(ChatDTO chatDTO) {
        return modelMapper.map(chatDTO, Chat.class);
    }

}
