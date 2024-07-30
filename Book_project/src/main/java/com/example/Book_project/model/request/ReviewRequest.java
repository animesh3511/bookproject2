package com.example.Book_project.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewRequest {

    private Long reviewId;
    private Long bookId;
    private Long userId;
    private Double rating;
    private String comment;

}
