package com.example.avochadbackend.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.example.avochadbackend.utility.enums.ChatType;
import java.util.Date;
import java.util.Objects;

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

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "last_message")
    @OneToOne(mappedBy = "chat", cascade = CascadeType.ALL)
    private String lastMessage;

    @Column(name = "channel")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "channel_id")
    private Channel channel;

    public Chat() {}

    public Chat(String title, ChatType type, Date createdAt, Date updatedAt, String lastMessage, Channel channel) {
        this.title = title;
        this.type = type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.lastMessage = lastMessage;
        this.channel = channel;
    }
    
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public ChatType getType() {
        return type;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(ChatType type) {
        this.type = type;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Chat)) return false;
        Chat chat = (Chat) o;
        return Objects.equals(id, chat.id) && Objects.equals(title, chat.title) && type == chat.type && Objects.equals(createdAt, chat.createdAt) && Objects.equals(updatedAt, chat.updatedAt) && Objects.equals(lastMessage, chat.lastMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, type, createdAt, updatedAt, lastMessage);
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", lastMessage='" + lastMessage + '\'' +
                '}';
    }

}
