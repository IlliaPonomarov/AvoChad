package com.example.avochadbackend.repo;

import com.example.avochadbackend.models.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepository extends JpaRepository<Long, Channel> {
}
