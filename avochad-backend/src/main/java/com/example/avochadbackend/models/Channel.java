package com.example.avochadbackend.models;

import javax.persistence.*;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.List;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "channel")
public class Channel {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    @Size(min = 2, max = 254, message = "Title must be between 2 and 254 characters")
    private String title;

    @OneToOne(mappedBy = "channel", cascade = CascadeType.ALL)
    @JoinColumn(name = "chat_id")
    @JsonBackReference
    private Chat chat;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_id")
    @JsonBackReference
    private Organization organization;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Channel)) return false;
        Channel channel = (Channel) o;

        return id.equals(channel.id) &&
                title.equals(channel.title) &&
                createdAt.equals(channel.createdAt) &&
                updatedAt.equals(channel.updatedAt) &&
                chat.equals(channel.chat) &&
                organization.equals(channel.organization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
