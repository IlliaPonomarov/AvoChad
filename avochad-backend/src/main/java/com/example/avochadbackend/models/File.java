package com.example.avochadbackend.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="file")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false, mappedBy = "message")
    private Message message;

    @Column(name = "hash")
    @NotEmpty(message = "Hash is mandatory")
    private String hash;

    public File() {}

    public File(Message message, String hash) {
        this.message = message;
        this.hash = hash;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof File)) return false;

        File file = (File) o;

        return id.equals(file.id) && hash.equals(file.hash) && message.equals(file.message); 
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", message=" + message +
                ", hash='" + hash + '\'' +
                '}';
    }
}
