package com.example.Book_project.repository;

import com.example.Book_project.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    boolean existsByBookId(Long bookId);

    List<Review> findByBookId(Long bookId);
}
