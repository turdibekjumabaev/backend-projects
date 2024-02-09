package org.project.store.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstname", nullable = false)
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "surname")
    private String surName;

    @Column(name = "birth")
    private LocalDate birthDate;

    @Column(name = "bio", columnDefinition = "VARCHAR(500)")
    private String bio;

    @Column(name = "username", columnDefinition = "VARCHAR(50) CHECK ( length(username) > 5 AND length(username) < 32 ) NOT NULL")
    private String username;

    @Column(name = "password", columnDefinition = "VARCHAR(30) CHECK ( length(password) > 7 )")
    private String password;
}
