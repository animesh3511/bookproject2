package com.example.Book_project.repository;

import com.example.Book_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);

    boolean existsByEmailAndUserIdNotIn(String email, List<Long> userIds);
}
