package com.example.bookstore.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private BigDecimal price;

    private String description;

    // Constructor with required fields
    public Book(String title, String author, BigDecimal price, String description) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.description = description;
    }
}
