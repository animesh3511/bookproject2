package com.example.Book_project.repository;

import com.example.Book_project.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findByTitleIn(List<String> bookList);

   // boolean existsByTitleAndIdNotIn(String title, List<Long> bookIds);

    boolean existsByTitleAndBookIdNotIn(String title, List<Long> bookIds);

    List<Book> searchBookByGenre(String genre);

    boolean existsByGenre(String genre);
}
