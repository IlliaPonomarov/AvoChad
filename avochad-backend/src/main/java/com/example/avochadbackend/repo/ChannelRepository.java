package com.example.avochadbackend.repo;

import com.example.avochadbackend.models.Channel;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
    Channel findByTitle(String title);
}
