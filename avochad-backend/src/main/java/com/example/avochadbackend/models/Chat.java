package com.example.avochadbackend.models;
import com.example.avochadbackend.utility.enums.ChatType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.List;
@Entity
@Table(name = "chat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name="title")
    @Size(min=1, max=120, message="Title should be greater then 1 and less then 120")
    private String title;

    @Column(name="description")
    @Size(min=1, max=120, message="Title should be greater then 1 and less then 120")
    private String description;

   @Column(name="linked_chat_id")
   @PositiveOrZero
   private Integer linkedChatId;

   @Column(name="has_private_forward")
   private Boolean hasPrivateForward;

   @Column(name="pinned_message_id")
   @PositiveOrZero
   private Integer pinnedMessageId;

   @Column(name="chat_type")
   @Enumerated(EnumType.ORDINAL)
   @NotNull
   private ChatType chatType;

    // CHANNELS - ONE TO MANY
    // PERSONS, CHATS - MANY TO MANY
    @ManyToMany
    @JoinTable(name = "person_chat",
    joinColumns = @JoinColumn(name = "chat_id"),
    inverseJoinColumns = @JoinColumn(name = "person_id"))
    private List<Person> persons;
    // CHATS, MESSAGES - ONE TO MANY
}
