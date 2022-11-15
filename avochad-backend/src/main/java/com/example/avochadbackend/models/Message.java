package com.example.avochadbackend.models;

import javax.persistence.*;

@Entity
@Table(name = "message")
public class Message {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message_type")
    private MessageType messageType;
}
