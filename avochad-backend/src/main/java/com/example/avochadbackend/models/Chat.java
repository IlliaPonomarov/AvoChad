package com.example.avochadbackend.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.example.avochadbackend.utility.enums.ChatType;

import java.util.Date;
import java.util.List;
@Entity
@Table(name = "chat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    @Size(min = 2, max = 254, message = "Title must be between 2 and 254 characters")
    private String title;

    @Column(name = "type")
    @NotNull(message = "Type is mandatory")
    @Enumerated(EnumType.ORDINAL)
    private ChatType type;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    
    
}
