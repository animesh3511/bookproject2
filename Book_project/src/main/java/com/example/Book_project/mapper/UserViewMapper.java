package com.example.Book_project.mapper;

import com.example.Book_project.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserViewMapper {

    public UserView toUserView(User user) {

        UserView userView = new UserView();
        userView.setUserId(user.getUserId());
        userView.setUserName(user.getUserName());
        userView.setEmail(user.getEmail());
        userView.setPassword(user.getPassword());
        userView.setCreatedAt(user.getCreatedAt());
        userView.setUpdatedAt(user.getUpdatedAt());
        userView.setIsActive(user.getIsActive());
        userView.setIsDeleted(user.getIsDeleted());
        userView.setRole(user.getRole());
        return userView;
    }

}
