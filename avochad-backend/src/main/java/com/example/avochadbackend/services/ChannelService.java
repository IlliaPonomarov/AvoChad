package com.example.avochadbackend.services;

import com.example.avochadbackend.dto.ChannelDTO;
import com.example.avochadbackend.models.Channel;
import com.example.avochadbackend.repo.ChannelRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.swing.text.html.Option;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ChannelService {

    private final ChannelRepository channelRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ChannelService(ChannelRepository channelRepository, ModelMapper modelMapper) {
        this.channelRepository = channelRepository;
        this.modelMapper = modelMapper;
    }


    public List<Channel> findAll(){
        return this.channelRepository.findAll();
    }

    public Optional<Channel> findById(long id) {
        return this.channelRepository.findById(id);
    }

    public Optional<Channel> findByTitle(String title) {
        return this.channelRepository.findByTitle(title);
    }
    @Transactional
    public void save(Channel channel) {
    
        channel.setCreatedAt(new Date());
        channel.setUpdatedAt(new Date());
        this.channelRepository.save(channel);
    }

    @Transactional
    public void update(Channel channel) {
        channel.setUpdatedAt(new Date());
        this.channelRepository.save(channel);
    }

    @Transactional
    public void deleteById(Long id) {
        this.channelRepository.deleteById(id);
    }

    public Channel convertChannelDtoToChannel(ChannelDTO channelDTO) {
        return this.modelMapper.map(channelDTO, Channel.class);
    }

}
