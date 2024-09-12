package com.recharging.booking_api.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_name")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    private Integer uid;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    @Column(name = "name")
    private String name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, message = "Password should be at least 6 characters")
    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Booking> booking;

    // Constructor
    public User() {} 

    public User(String name, String email, String password) {
        this();
        this.name = name;
        this.email = email;
        this.password = password;
    }
}