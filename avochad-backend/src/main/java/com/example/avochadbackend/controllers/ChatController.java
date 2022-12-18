package com.example.avochadbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
import com.example.avochadbackend.models.Chat;
import com.example.avochadbackend.models.User;
import com.example.avochadbackend.services.ChatService;
import com.example.avochadbackend.services.UserService;
import com.example.avochadbackend.utility.exception.ErrorResponse;
import com.example.avochadbackend.utility.exception.userExceptions.UserNotFoundException;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;
    private final UserService userService;

    @Autowired
    public ChatController(ChatService chatService, UserService userService) {
        this.chatService = chatService;
        this.userService = userService;
    }

    @GetMapping("/")
    public List<Chat> findAll() {
        return chatService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chat> findChatById(@PathVariable long id) {
        Optional<Chat> chat = chatService.findById(id);

        if(chat.isEmpty())
            throw new UserNotFoundException("Chat with id " + id + " not found");

        return new ResponseEntity<>(chat.get(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public List<Chat> findChatsByUserId(@PathVariable long id) {
        Optional<User> user = userService.findById(id);

        if(user.isEmpty())
            throw new UserNotFoundException("User with id " + id + " not found");

        return chatService.findByUserId(id);
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


}
