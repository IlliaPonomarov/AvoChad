package com.example.avochadbackend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.avochadbackend.dto.ChannelDTO;
import com.example.avochadbackend.models.Channel;
import com.example.avochadbackend.services.ChannelService;
import com.example.avochadbackend.utility.exception.ErrorResponse;
import com.example.avochadbackend.utility.exception.channelExceptions.ChannelNotCreatedException;
import com.example.avochadbackend.utility.exception.channelExceptions.ChannelNotFoundException;

@RestController
@RequestMapping("/api/channels")
public class ChannelController {
    
    private final ChannelService channelService;

    @Autowired
    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }


    @GetMapping("/")
    public ResponseEntity<List<Channel>> getAllChannels() {

        List<Channel> channels = channelService.findAll();

        if(channels.size() == 0)
            throw new ChannelNotFoundException("No channels found");

        return new ResponseEntity(channels, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Channel> getChannelById(long id) {

        Optional<Channel> channel = channelService.findById(id);

        if(channel.isEmpty())
            throw new ChannelNotFoundException("Channel with id " + id + " not found");

        return new ResponseEntity<Channel>(channel.get(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Channel> createChannel(@RequestBody ChannelDTO channelDTO, BindingResult bindingResult) {


        if(bindingResult.hasErrors()) {

            StringBuilder messgaeError = new StringBuilder();

            for(FieldError error : bindingResult.getFieldErrors()) 
                messgaeError.append(error.getField() + " " + error.getDefaultMessage() + ";");
            
            throw new ChannelNotCreatedException(messgaeError.toString());
        }

        Channel channel = channelService.convertChannelDtoToChannel(channelDTO);

        channelService.save(channel);
        

        return new ResponseEntity<Channel>(channel, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Channel> updateChannel(@PathVariable Long id, @RequestBody ChannelDTO channelDTO, BindingResult bindingResult) {
        Optional<Channel> channel = channelService.findById(id);

        if(bindingResult.hasErrors() || channel.isEmpty()) {

            StringBuilder messgaeError = new StringBuilder();

            for(FieldError error : bindingResult.getFieldErrors()) 
                messgaeError.append(error.getField() + " " + error.getDefaultMessage() + ";");
            
            throw new ChannelNotCreatedException(messgaeError.toString());
        }

        Channel newChannel = channelService.convertChannelDtoToChannel(channelDTO);

        channelService.update(newChannel);

        return new ResponseEntity<Channel>(newChannel, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Channel> deleteChannel(@PathVariable Long id) {
        Optional<Channel> channel = channelService.findById(id);

        if(channel.isEmpty())
            throw new ChannelNotFoundException("Channel with id " + id + " not found");

        channelService.deleteById(id);

        return new ResponseEntity<Channel>(HttpStatus.OK);
    }

    @ExceptionHandler(ChannelNotCreatedException.class)
    public ResponseEntity<ErrorResponse> handleChannelNotCreatedException(ChannelNotCreatedException e) {
        return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getMessage(), System.currentTimeMillis()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ChannelNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleChannelNotFoundException(ChannelNotFoundException e) {
        return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getMessage(), System.currentTimeMillis()), HttpStatus.NOT_FOUND);
    }
}
