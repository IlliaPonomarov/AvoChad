package com.example.avochadbackend.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "channel")
public class Channel {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    @Size(min = 5, max = 120, message = "Title should be greater then 5 and less then 120 ")
    private String title;

    @Column(name = "description")
    @Size(min = 1, max = 1000, message = "Description should be greater then 1 and less then 1000 ")
    private String description;

    @ManyToMany
    @JoinTable(name = "channel_person",
    joinColumns = @JoinColumn(name = "channel_id"),
    inverseJoinColumns = @JoinColumn(name =  "person_id"))
    private List<Person> persons;

    public Channel() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Channel)) return false;
        Channel channel = (Channel) o;
        return Objects.equals(id, channel.id) && Objects.equals(title, channel.title) && Objects.equals(description, channel.description) && Objects.equals(persons, channel.persons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, persons);
    }
}
