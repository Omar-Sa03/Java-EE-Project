package com.example.bookstore.controllers;

import com.example.bookstore.models.Book;
import com.example.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public String listBooks(Model model, @RequestParam(required = false) String search) {
        List<Book> books;
        
        if (search != null && !search.isEmpty()) {
            books = bookService.searchBooksByTitle(search);
            if (books.isEmpty()) {
                books = bookService.searchBooksByAuthor(search);
            }
        } else {
            books = bookService.getAllBooks();
        }
        
        model.addAttribute("books", books);
        return "books";
    }
}
