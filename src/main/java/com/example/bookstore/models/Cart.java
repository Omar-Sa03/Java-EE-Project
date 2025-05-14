package com.example.bookstore.models;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {

    private List<CartItem> items = new ArrayList<>();

    // Add a book to the cart
    public void addItem(Book book, int quantity) {
        // Check if the book is already in the cart
        for (CartItem item : items) {
            if (item.getBook().getId().equals(book.getId())) {
                // Update quantity if book already exists in cart
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        // Add new item if book not already in cart
        items.add(new CartItem(book, quantity));
    }

    // Remove a book from the cart
    public void removeItem(Long bookId) {
        items.removeIf(item -> item.getBook().getId().equals(bookId));
    }

    // Update the quantity of a book in the cart
    public void updateItemQuantity(Long bookId, int quantity) {
        for (CartItem item : items) {
            if (item.getBook().getId().equals(bookId)) {
                if (quantity <= 0) {
                    removeItem(bookId);
                } else {
                    item.setQuantity(quantity);
                }
                return;
            }
        }
    }

    // Clear the cart
    public void clear() {
        items.clear();
    }

    // Calculate the total price of all items in the cart
    public BigDecimal getTotal() {
        return items.stream()
                .map(CartItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Get the total number of items in the cart
    public int getItemCount() {
        return items.stream()
                .mapToInt(CartItem::getQuantity)
                .sum();
    }
}
