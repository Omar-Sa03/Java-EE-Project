package com.example.bookstore.repositories;

import com.example.bookstore.models.Order;
import com.example.bookstore.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserOrderByOrderDateDesc(User user);
}
