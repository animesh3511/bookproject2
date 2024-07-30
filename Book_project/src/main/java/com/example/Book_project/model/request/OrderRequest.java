package com.example.Book_project.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderRequest {

    private Long orderId;
    private Long userId;
    private LocalDate orderDate;
    private Double totalAmount;
    private String status;
    private List<String> bookList;




}
