package org.project.store.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "authors")
public class Author {
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

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate birthDate;

    @Column(name = "date_of_death")
    private LocalDate deathDate;

    @Column(name = "bio", columnDefinition = "VARCHAR(500)")
    private String bio;

    @Column(columnDefinition = "VARCHAR(50) CHECK ( length(username) > 5 AND length(username) < 32 ) NOT NULL")
    private String username;

    @Column(columnDefinition = "VARCHAR(30) CHECK ( length(password) > 7 )")
    private String password;

    @ManyToMany
    @JoinTable(
            name = "authors_books",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Book> books;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
}
