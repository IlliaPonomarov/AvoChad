package com.example.avochadbackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.avochadbackend.models.Channel;

import java.util.Optional;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
    Optional<Channel> findByTitle(String title);
}
