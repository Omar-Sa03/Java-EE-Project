package com.example.bookstore.services;

import com.example.bookstore.models.Book;
import com.example.bookstore.models.Cart;
import com.example.bookstore.models.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.List;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartService {

    private final Cart cart = new Cart();
    
    @Autowired
    private BookService bookService;

    public void addItem(Long bookId, int quantity) {
        Book book = bookService.getBookById(bookId);
        cart.addItem(book, quantity);
    }

    public void removeItem(Long bookId) {
        cart.removeItem(bookId);
    }

    public void updateItemQuantity(Long bookId, int quantity) {
        cart.updateItemQuantity(bookId, quantity);
    }

    public void clear() {
        cart.clear();
    }

    public List<CartItem> getItems() {
        return cart.getItems();
    }

    public BigDecimal getTotal() {
        return cart.getTotal();
    }

    public int getItemCount() {
        return cart.getItemCount();
    }

    public boolean isEmpty() {
        return cart.getItems().isEmpty();
    }
}
