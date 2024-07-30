package com.example.Book_project.service;

import com.example.Book_project.model.OrderStatus;
import com.example.Book_project.model.request.BookRequest;
import com.example.Book_project.model.request.OrderRequest;
import com.example.Book_project.model.request.ReviewRequest;
import com.example.Book_project.model.request.UserRequest;

public interface MasterService {
    Object saveOrUpdateUser(UserRequest userRequest);

    Object saveOrUpdateBook(BookRequest bookRequest);

    Object saveOrUpdateOrder(OrderRequest orderRequest);

    Object saveOrUpdateReview(ReviewRequest reviewRequest);

    Object findBookById(Long bookId) throws Exception;

    Object deleteBookById(Long bookId);

    Object searchBookByGenre(String genre);

    Object findUserById(Long userId);

    Object deleteUserById(Long userId);

    Object findOrderById(Long orderId) throws Exception;

    Object findOrderByUserId(Long userId);

    Object changeOrderStatus(Long orderId, OrderStatus newStatus) throws Exception;

    Object getReviewByBookId(Long bookId) throws Exception;
}
