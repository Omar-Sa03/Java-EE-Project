package com.example.bookstore.controllers;

import com.example.bookstore.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/cart")
    public String viewCart(Model model) {
        model.addAttribute("cartItems", cartService.getItems());
        model.addAttribute("total", cartService.getTotal());
        return "cart";
    }

    @PostMapping("/cart/add/{bookId}")
    public String addToCart(@PathVariable Long bookId, @RequestParam(defaultValue = "1") int quantity) {
        cartService.addItem(bookId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/cart/update/{bookId}")
    public String updateCartItem(@PathVariable Long bookId, @RequestParam int quantity) {
        cartService.updateItemQuantity(bookId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/cart/remove/{bookId}")
    public String removeFromCart(@PathVariable Long bookId) {
        cartService.removeItem(bookId);
        return "redirect:/cart";
    }

    @PostMapping("/cart/clear")
    public String clearCart() {
        cartService.clear();
        return "redirect:/cart";
    }
}
