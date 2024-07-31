package com.example.Book_project.serviceimpl;

import com.example.Book_project.model.*;
import com.example.Book_project.model.request.BookRequest;
import com.example.Book_project.model.request.OrderRequest;
import com.example.Book_project.model.request.ReviewRequest;
import com.example.Book_project.model.request.UserRequest;
import com.example.Book_project.repository.BookRepository;
import com.example.Book_project.repository.OrderRepository;
import com.example.Book_project.repository.ReviewRepository;
import com.example.Book_project.repository.UserRepository;
import com.example.Book_project.service.MasterService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MasterServiceImpl implements MasterService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Object saveOrUpdateUser(UserRequest userRequest) {

        if (userRepository.existsById(userRequest.getUserId())) {
            User user = userRepository.findById(userRequest.getUserId()).get();
            user.setUserId(userRequest.getUserId());
            user.setUserName(userRequest.getUserName());
            List<Long> userIds = new ArrayList<>();
            userIds.add(userRequest.getUserId());
            if (userRepository.existsByEmailAndUserIdNotIn(userRequest.getEmail(), userIds)) {
                return "email already exists";
            } else {
                user.setEmail(userRequest.getEmail());
            }
            user.setPassword(hashPassword(userRequest.getPassword()));
            user.setRole(userRequest.getRole());
            userRepository.save(user);
            return "User details are updated";

        } else {
            User user = new User();
            user.setUserName(userRequest.getUserName());
            if (userRepository.existsByEmail(userRequest.getEmail())) {
                return "email already exists";
            } else {
                user.setEmail(userRequest.getEmail());
            }
            user.setPassword(hashPassword(userRequest.getPassword()));
            user.setRole(userRequest.getRole());
            user.setIsActive(true);
            user.setIsDeleted(false);
            userRepository.save(user);
            return "User Saved";
        }
//saveOrUpdateUser() ends here
    }

    @Override
    public Object saveOrUpdateBook(BookRequest bookRequest) {

        if (bookRepository.existsById(bookRequest.getBookId())) {
            Book book = bookRepository.findById(bookRequest.getBookId()).get();
            book.setBookId(bookRequest.getBookId());
            List<Long> bookIds = new ArrayList<>();
            bookIds.add(bookRequest.getBookId());
            if (bookRepository.existsByTitleAndBookIdNotIn(bookRequest.getTitle(), bookIds)) {
                return "Book Title already exists";
            } else {
                book.setTitle(bookRequest.getTitle());
            }
            book.setStockQuantity(bookRequest.getStockQuantity());
            book.setPrice(bookRequest.getPrice());
            book.setPublishedDate(bookRequest.getPublishedDate());
            book.setGenre(bookRequest.getGenre());
            book.setAuthor(bookRequest.getAuthor());
            bookRepository.save(book);
            return "Book Details are updated";
        } else {
            Book book = new Book();
            book.setTitle(bookRequest.getTitle());
            book.setAuthor(bookRequest.getAuthor());
            book.setGenre(bookRequest.getGenre());
            book.setPrice(bookRequest.getPrice());
            book.setPublishedDate(bookRequest.getPublishedDate());
            book.setStockQuantity(bookRequest.getStockQuantity());
            book.setIsActive(true);
            book.setIsDeleted(false);
            bookRepository.save(book);
            return "Book Saved";
        }
        //  saveOrUpdateBook() ends here
    }

    @Override
    public Object saveOrUpdateOrder(OrderRequest orderRequest) {

        List<Book> books = bookRepository.findByTitleIn(orderRequest.getBookList());
        Double totalAmount = 0.0;
        List<Order> ordersToSave = new ArrayList<>();
        for (Book b : books) {
            Order order = new Order();
            order.setUserId(orderRequest.getUserId());
            order.setStatus(orderRequest.getStatus());
            order.setOrderDate(orderRequest.getOrderDate());
            order.setIsDeleted(false);
            order.setIsActive(true);
            order.setBookList(b.getTitle());
            totalAmount = totalAmount + b.getPrice();
            ordersToSave.add(order);
        }
        for (Order order : ordersToSave) {
            order.setTotalAmount(totalAmount);
        }
        orderRepository.saveAll(ordersToSave);
        return "Order Saved";

        //saveOrUpdateOrder() ends here
    }

    @Override
    public Object saveOrUpdateReview(ReviewRequest reviewRequest) {

        if (reviewRepository.existsById(reviewRequest.getReviewId())) {
            Review review = reviewRepository.findById(reviewRequest.getReviewId()).get();
            review.setComment(reviewRequest.getComment());
            review.setRating(reviewRequest.getRating());
            review.setBookId(reviewRequest.getBookId());
            review.setUserId(reviewRequest.getUserId());
            review.setReviewId(reviewRequest.getReviewId());
            reviewRepository.save(review);
            return "Your review is updated";
        } else {
            Review review = new Review();
            review.setUserId(reviewRequest.getUserId());
            review.setBookId(reviewRequest.getBookId());
            review.setRating(reviewRequest.getRating());
            review.setComment(reviewRequest.getComment());
            review.setIsActive(true);
            review.setIsDeleted(false);
            reviewRepository.save(review);
            return "Review Saved";
        }
        // saveOrUpdateReview() ends here
    }

    @Override
    public Object findBookById(Long bookId) throws Exception {
        if (bookId != null) {
            return bookRepository.findById(bookId).orElseThrow(() -> new Exception("Book not found with given bookId"));
        } else {
            return "bookId is null";
        }
    }


    @Override
    public Object deleteBookById(Long bookId) {

        if (bookRepository.existsById(bookId)) {
            bookRepository.deleteById(bookId);
            return "Book Deleted";
        } else {
            return "book not found";
        }
    }

    @Override
    public Object searchBookByGenre(String genre) {
        if (bookRepository.existsByGenre(genre)) {
            return bookRepository.searchBookByGenre(genre);
        } else {
            return "Genre not found";
        }
    }

    @Override
    public Object findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ExpressionException("User not found"));
    }

    @Override
    public Object deleteUserById(Long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return "User deleted succesfully";
        } else {
            return "User not found";
        }
        //deleteUserById() ends here
    }

    @Override
    public Object findOrderById(Long orderId) throws Exception {
        return orderRepository.findById(orderId).orElseThrow(() -> new Exception("Order with given id not found"));

    }

    @Override
    public Object findOrderByUserId(Long userId) {
        if (orderRepository.existsByUserId(userId)) {
            return orderRepository.findByUserId(userId);
        } else {
            return "UserId not found";
        }
    }

    @Override
    public Object changeOrderStatus(Long orderId, OrderStatus newStatus) throws Exception {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new Exception("Order not found"));
        if (!isValidTransition(order.getStatus(), newStatus)) {
            throw new Exception("Invalid status transition");
        }
        //converting enum to string by 'name()' mwthod of 'enum' class
        String newStatus1 = newStatus.name();
        order.setStatus(newStatus1);
        orderRepository.save(order);
        return "Your order's status has been changed";

        //changeOrderStatus() ends here
    }

    @Override
    public Object getReviewByBookId(Long bookId) throws Exception {
        if (reviewRepository.existsByBookId(bookId)) {
            return reviewRepository.findByBookId(bookId);
        } else {
            return "review not found for given bookId";
        }
        //getReviewByBookId() ends here
    }

    public boolean isValidTransition(String status, OrderStatus newStatus) {

        if (status.equalsIgnoreCase("PENDING")) {
            return newStatus == OrderStatus.PROCESSING || newStatus == OrderStatus.CANCELLED;
        } else if (status.equalsIgnoreCase("PROCESSING")) {
            return newStatus == OrderStatus.SHIPPED || newStatus == OrderStatus.CANCELLED;
        } else if (status.equalsIgnoreCase("SHIPPED")) {
            return newStatus == OrderStatus.DELIVERED || newStatus == OrderStatus.CANCELLED;
        } else if (status.equalsIgnoreCase("DELIVERED")) {
            return newStatus == OrderStatus.RETURNED || newStatus == OrderStatus.CANCELLED;
        } else {
            return false;
        }

//isValidTransition() ends here
    }

    public String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    //class ends here
}
