package com.example.Book_project.controller;

import com.example.Book_project.mapper.UserViewMapper;
import com.example.Book_project.model.User;
import com.example.Book_project.model.request.AuthRequest;
import com.example.Book_project.serviceimpl.UserDetailsImpl;
import com.example.Book_project.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthApi {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserViewMapper userViewMapper;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {

        try {

            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(),
                    authRequest.getPassword()));
            UserDetailsImpl userDetails = (UserDetailsImpl) authenticate.getPrincipal();
            User user = userDetails.getUser();
            String userName = user.getUserName();

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, jwtTokenUtil.generateToken(userName))
                    .body(userViewMapper.toUserView(user));
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }
}
