package com.example.avochadbackend.models;

import com.example.avochadbackend.models.enums.LanguageCode;
import com.example.avochadbackend.models.enums.Status;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Objects;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "avatar_file_id")
    private File avatarFileId;

    @Column(name = "first_name")
    @NotBlank
    @Size(min = 1, max = 50, message = "First Name should be greater then 1 and less then 120")
    private String firstName;

    @Column(name = "second_name")
    @NotBlank
    @Size(min = 1, max = 120, message = "Second Name should be greater then 1 and less then 120")
    private String secondName;

    @Column(name = "username")
    @NotBlank
    @Size(min = 1, max = 120, message = "Username should be greater then 1 and less then 120")
    private String username;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "age")
    @Size(min = 1, max = 120, message = "Age should be greater then 1 and less then 120")
    private int age;

    @Column(name = "password")
    @NotBlank
    @Size(min = 1, max = 120, message = "Password should be greater then 1 and less then 120")
    private String password;

    @Column(name = "language_code")
    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private LanguageCode languageCode;

    @Column(name="status")
    @Enumerated(EnumType.ORDINAL)
    @NotNull
    @PositiveOrZero
    private Status status;

    // add list of chats ( many to many )
    // add list of channels ( many to many )
    // add list of messages ( many to one )

    public Person() {}

    public Person(String firstName, String secondName, int age, String username, String email, String password, LanguageCode languageCode, Status status) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.username = username;
        this.email = email;
        this.password = password;
        this.languageCode = languageCode;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public File getAvatarFileId() {
        return avatarFileId;
    }
    public void setAvatarFileId(File avatarFileId) {
        this.avatarFileId = avatarFileId;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LanguageCode getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(LanguageCode languageCode) {
        this.languageCode = languageCode;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return age == person.age && id.equals(person.id) && Objects.equals(avatarFileId, person.avatarFileId) && firstName.equals(person.firstName) && secondName.equals(person.secondName) && username.equals(person.username) && email.equals(person.email) && password.equals(person.password) && languageCode == person.languageCode && status == person.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, avatarFileId, firstName, secondName, username, email, age, password, languageCode, status);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", avatarFileId=" + avatarFileId +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", languageCode=" + languageCode +
                ", status=" + status +
                '}';
    }
}
