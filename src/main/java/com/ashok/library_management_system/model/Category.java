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
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name", unique = true, nullable = false, length = 100)
    @NotBlank(message = "Category name cannot be blank!")
    @Size(min = 3, max = 100, message = "Category name must be between 3 and 100 characters!")
    private String categoryName;

    @ManyToMany(mappedBy = "categories", cascade = CascadeType.ALL)
    private Set<Book> books = new HashSet<Book>();
}
