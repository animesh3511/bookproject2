package com.example.Book_project.repository;

import com.example.Book_project.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByUserId(Long userId);



    boolean existsByUserId(Long userId);
}
