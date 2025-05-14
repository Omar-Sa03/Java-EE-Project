package com.example.bookstore.controllers;

import com.example.bookstore.models.Order;
import com.example.bookstore.models.User;
import com.example.bookstore.services.CartService;
import com.example.bookstore.services.OrderService;
import com.example.bookstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @GetMapping("/checkout")
    public String checkout(Model model) {
        if (cartService.isEmpty()) {
            return "redirect:/cart";
        }
        
        model.addAttribute("cartItems", cartService.getItems());
        model.addAttribute("total", cartService.getTotal());
        return "checkout";
    }

    @PostMapping("/order/create")
    public String createOrder() {
        if (cartService.isEmpty()) {
            return "redirect:/cart";
        }
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        
        try {
            User user = userService.findByEmail(email);
            Order order = orderService.createOrder(user);
            return "redirect:/order/confirmation/" + order.getId();
        } catch (RuntimeException e) {
            // Si l'utilisateur n'est pas trouvé, rediriger vers la page de connexion
            return "redirect:/login?error=userNotFound";
        }
    }

    @GetMapping("/order/confirmation/{orderId}")
    public String orderConfirmation(@PathVariable Long orderId, Model model) {
        Order order = orderService.getOrderById(orderId);
        model.addAttribute("order", order);
        return "order-confirmation";
    }

    @GetMapping("/orders")
    public String viewOrders(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        
        try {
            User user = userService.findByEmail(email);
            List<Order> orders = orderService.getUserOrders(user);
            model.addAttribute("orders", orders);
            return "orders";
        } catch (RuntimeException e) {
            // Si l'utilisateur n'est pas trouvé, rediriger vers la page de connexion
            return "redirect:/login?error=userNotFound";
        }
    }

    @GetMapping("/order/{orderId}")
    public String viewOrderDetails(@PathVariable Long orderId, Model model) {
        Order order = orderService.getOrderById(orderId);
        model.addAttribute("order", order);
        return "order-details";
    }
}
