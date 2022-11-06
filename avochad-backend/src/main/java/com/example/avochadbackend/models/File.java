package com.example.avochadbackend.models;

import javax.persistence.*;

@Entity
@Table(name="file")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Person personAvatar;

    public File() {}
}
