package com.example.bookstore.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private BigDecimal price;

    // Constructor with required fields
    public OrderItem(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
        this.price = book.getPrice();
    }

    // Calculate subtotal
    public BigDecimal getSubtotal() {
        return price.multiply(new BigDecimal(quantity));
    }
}
