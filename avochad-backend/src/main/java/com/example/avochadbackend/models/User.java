package com.example.avochadbackend.models;
import com.example.avochadbackend.utility.enums.Status;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    @NotBlank(message = "First name is mandatory")
    @Size(min = 2, max = 254, message = "First name must be between 2 and 254 characters")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "Last name is mandatory")
    @Size(min = 2, max = 254, message = "Last name must be between 2 and 254 characters")
    private String lastName;


    @Column(name = "email")
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email is not valid")
    private String email;

    @Column(name = "username")
    @NotBlank(message = "Username is mandatory")
    @Size(min = 2, max = 254, message = "Username must be between 2 and 254 characters")
    private String username;

    @Column(name = "password")
    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, max = 30, message = "Password must be between 8 and 30 characters")
    private String password;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    @NotNull(message = "Status is mandatory")
    private Status status;

    public User() {}

    public User(String firstName, String lastName, String email, String username, String password, Status status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public Long getId() {
        return id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object user) {
        if( this == user) return true;

        if(!(user instanceof User)) return false;

        User user1 = (User) user;

        return id.equals(user1.id) && firstName.equals(user1.firstName) && lastName.equals(user1.lastName) && email.equals(user1.email) && username.equals(user1.username) && password.equals(user1.password) && createdAt.equals(user1.createdAt) && updatedAt.equals(user1.updatedAt) && status.equals(user1.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, username, password, createdAt, updatedAt, status);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' + 
                ", username='" + username + '\'' 
                + ", status='" + status + '\'';
    }


}
