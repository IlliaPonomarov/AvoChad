package com.example.avochadbackend.utility.exception.chatExceptions;

public class ChatNotFoundException extends RuntimeException{
    
    public ChatNotFoundException(String message) {
        super(message);
    }
}