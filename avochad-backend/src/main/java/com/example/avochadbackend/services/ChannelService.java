package com.example.avochadbackend.services;

import com.example.avochadbackend.models.Channel;
import com.example.avochadbackend.repo.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ChannelService {

    private final ChannelRepository channelRepository;

    @Autowired
    public ChannelService(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
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
    public void save(Optional<Channel> channel) {
        if (channel.isEmpty())
            return;

        this.channelRepository.save(channel.get());
    }

    @Transactional
    public void update(Optional<Channel> channel) {
        if (channel.isEmpty())
            return;

        this.channelRepository.save(channel.get());
    }
}
