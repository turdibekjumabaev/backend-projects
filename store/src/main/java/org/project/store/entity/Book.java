package org.project.store.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String bookName;

    @ManyToMany(mappedBy = "books")
    private Set<Author> authors;

    @Column(name = "publication_year")
    private LocalDate publicationYear;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "isbn")
    private String ISBN;

    @Column(name = "annotation", columnDefinition = "TEXT")
    private String annotation;

    @Column(name = "number_of_pages")
    private Integer numberOfPages;

    @ManyToMany(mappedBy = "books")
    private Set<Language> languages;
}
