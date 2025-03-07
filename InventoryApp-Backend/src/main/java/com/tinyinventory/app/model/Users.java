package com.tinyinventory.app.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
/*
@Table(name = "users", uniqueConstraints = {
    @UniqueConstraint(name = "uk_users_username", columnNames = "username")
}) // Ensures username uniqueness at the database level *** Not sure if I want this
*/
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment primary key
    @Column(nullable = false, unique = true)
    private Integer id; // Use Integer instead of int to handle null values before persisting

    @Column(nullable = false, unique = true) // Unique and not null
    private String username;

    @Column(nullable = false) // Ensures password is required
    private String password;

    private String full_name; // Can be nullable

    @Column(unique = true) // Ensures email uniqueness but allows null
    private String email;



}
