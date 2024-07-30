package com.example.Book_project.model.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookRequest {

    private Long bookId;
    private String title;
    private String author;
    private String genre;
    private Double price;
    private LocalDate publishedDate;
    private Long stockQuantity;

}
