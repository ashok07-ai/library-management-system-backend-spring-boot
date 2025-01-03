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
@Table(name = "publishers")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="publisher_name", unique = true, nullable = false, length = 50)
    @NotBlank(message = "Publisher name is required!")
    @Size(min = 3, max = 50, message = "Publisher name must be between 3 and 50 characters!")
    private String publisherName;

    @ManyToMany(mappedBy = "publishers",cascade = CascadeType.ALL)
    private Set<Book> books = new HashSet<Book>();
}
