package com.example.bookstore.services;

import com.example.bookstore.models.*;
import com.example.bookstore.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartService cartService;

    @Transactional
    public Order createOrder(User user) {
        // Check if cart is empty
        if (cartService.isEmpty()) {
            throw new RuntimeException("Cannot create order with empty cart");
        }

        // Create new order
        Order order = new Order(user);
        order.setOrderDate(LocalDateTime.now());

        // Add items from cart to order
        for (CartItem cartItem : cartService.getItems()) {
            OrderItem orderItem = new OrderItem(cartItem.getBook(), cartItem.getQuantity());
            order.addOrderItem(orderItem);
        }

        // Save order
        Order savedOrder = orderRepository.save(order);

        // Clear cart after order is created
        cartService.clear();

        return savedOrder;
    }

    public List<Order> getUserOrders(User user) {
        return orderRepository.findByUserOrderByOrderDateDesc(user);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }
}
