package com.example.avochadbackend.models;

import javax.persistence.*;

@Entity
@Table(name="file")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false, mappedBy = "avatarFileId")
    private Person personAvatar;

    public File() {}
}
