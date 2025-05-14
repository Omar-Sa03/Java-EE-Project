package com.example.bookstore.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    private Book book;
    private int quantity;

    public BigDecimal getSubtotal() {
        return book.getPrice().multiply(new BigDecimal(quantity));
    }
}
