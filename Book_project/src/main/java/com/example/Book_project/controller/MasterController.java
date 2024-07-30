package com.example.Book_project.controller;

import com.example.Book_project.model.OrderStatus;
import com.example.Book_project.model.request.BookRequest;
import com.example.Book_project.model.request.OrderRequest;
import com.example.Book_project.model.request.ReviewRequest;
import com.example.Book_project.model.request.UserRequest;
import com.example.Book_project.model.response.CustomEntityResponse;
import com.example.Book_project.model.response.EntityResponse;
import com.example.Book_project.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MasterController {

    @Autowired
    private MasterService masterService;

    @PostMapping("/saveOrUpdateUser")
    public ResponseEntity<?> saveOrUpdateUser(@RequestBody UserRequest userRequest) {
        try {
            return new ResponseEntity<>(new EntityResponse(masterService.saveOrUpdateUser(userRequest), 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomEntityResponse(e.getMessage(), -1), HttpStatus.BAD_REQUEST);
        }
//saveOrUpdateUser() method ends here
    }

    @GetMapping("/findUserById")
    public ResponseEntity<?> findUserById(@RequestParam Long userId) {
        try {
            return new ResponseEntity<>(new EntityResponse(masterService.findUserById(userId), 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomEntityResponse(e.getMessage(), -1), HttpStatus.BAD_REQUEST);
        }
        // findUserById() ends here
    }

    @DeleteMapping("/deleteUserById")
    public ResponseEntity<?> deleteUserById(@RequestParam Long userId) {
        try {
            return new ResponseEntity<>(new EntityResponse(masterService.deleteUserById(userId), 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomEntityResponse(e.getMessage(), -1), HttpStatus.BAD_REQUEST);
        }
//deleteUserById() method ends here
    }

    @PostMapping("/saveOrUpdateBook")
    public ResponseEntity<?> saveOrUpdateBook(@RequestBody BookRequest bookRequest) {
        try {
            return new ResponseEntity<>(new EntityResponse(masterService.saveOrUpdateBook(bookRequest), 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomEntityResponse(e.getMessage(), -1), HttpStatus.BAD_REQUEST);
        }
        // saveOrUpdateBook() ends here
    }

    @GetMapping("/findBookById")
    public ResponseEntity<?> findBookById(@RequestParam Long bookId) {
        try {
            return new ResponseEntity<>(new EntityResponse(masterService.findBookById(bookId), 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomEntityResponse(e.getMessage(), -1), HttpStatus.BAD_REQUEST);
        }
//findBookById() ends here
    }

    @DeleteMapping("/deleteBookById")
    public ResponseEntity<?> deleteBookById(@RequestParam Long bookId) {
        try {
            return new ResponseEntity<>(new EntityResponse(masterService.deleteBookById(bookId), 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomEntityResponse(e.getMessage(), -1), HttpStatus.BAD_REQUEST);
        }
        // deleteBookById() ends here
    }

    @GetMapping("/searchBookByGenre")
    public ResponseEntity<?> searchBookByGenre(@RequestParam String genre) {
        try {
            return new ResponseEntity<>(new EntityResponse(masterService.searchBookByGenre(genre), 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomEntityResponse(e.getMessage(), -1), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/saveOrUpdateOrder")
    public ResponseEntity<?> saveOrUpdateOrder(@RequestBody OrderRequest orderRequest) {
        try {
            return new ResponseEntity<>(new EntityResponse(masterService.saveOrUpdateOrder(orderRequest), 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomEntityResponse(e.getMessage(), -1), HttpStatus.BAD_REQUEST);
        }
        //saveOrUpdateOrder() ends here
    }

    @GetMapping("/findOrderById")
    public ResponseEntity<?> findOrderById(@RequestParam Long orderId) {
        try {
            return new ResponseEntity<>(new EntityResponse(masterService.findOrderById(orderId), 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomEntityResponse(e.getMessage(), -1), HttpStatus.BAD_REQUEST);
        }
        //findOrderById() ends here
    }

    @GetMapping("/findOrderByUserId")
    public ResponseEntity<?> findOrderByUserId(@RequestParam Long userId) {
        try {
            return new ResponseEntity<>(new EntityResponse(masterService.findOrderByUserId(userId), 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomEntityResponse(e.getMessage(), -1), HttpStatus.BAD_REQUEST);
        }
        //findOrderByUserId()ends here
    }

    @PostMapping("/changeOrderStatus")
    public ResponseEntity<?> changeOrderStatus(@RequestParam Long orderId, @RequestParam OrderStatus newStatus) {
        try {
            return new ResponseEntity<>(new EntityResponse(masterService.changeOrderStatus(orderId, newStatus), 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomEntityResponse(e.getMessage(), -1), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/saveOrUpdateReview")
    public ResponseEntity<?> saveOrUpdateReview(@RequestBody ReviewRequest reviewRequest) {
        try {
            return new ResponseEntity<>(new EntityResponse(masterService.saveOrUpdateReview(reviewRequest), 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomEntityResponse(e.getMessage(), -1), HttpStatus.BAD_REQUEST);
        }
//saveOrUpdateReview() ends here
    }

    @GetMapping("/getReviewByBookId")
    public ResponseEntity<?> getReviewByBookId(@RequestParam Long bookId) {
        try {
            return new ResponseEntity<>(new EntityResponse(masterService.getReviewByBookId(bookId), 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomEntityResponse(e.getMessage(), -1), HttpStatus.BAD_REQUEST);
        }


        // getReviewByBookId() ends here
    }

//class ends here
}
