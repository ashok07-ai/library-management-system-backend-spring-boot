package com.ashok.library_management_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="book_isbn", unique = true, nullable = false, length = 50)
    @NotBlank(message = "ISBN cannot be blank!")
    @Size(min = 3, max = 50, message = "ISBN value must be between 3 and 50 characters!")
    private String bookISBN;

    @Column(name="book_name", unique = true, nullable = false, length = 100)
    @NotBlank(message = "Book name cannot be blank!")
    @Size(min = 5, max = 100, message = "Book name must be between 5 and 100 characters!")
    private String bookName;

    @Column(name = "book_description", nullable = true)
    private String bookDescription;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "books_authors",
    joinColumns = {@JoinColumn(name = "book_id")},
    inverseJoinColumns = {@JoinColumn(name = "author_id")})
    private Set<Author> authors = new HashSet<Author>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "books_categories",
    joinColumns = {@JoinColumn(name = "book_id")},
    inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private Set<Category> categories = new HashSet<Category>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "books_publishers",
    joinColumns = {@JoinColumn(name = "book_id")},
    inverseJoinColumns = {@JoinColumn(name = "publisher_id")})
    private Set<Publisher> publishers = new HashSet<Publisher>();
}
