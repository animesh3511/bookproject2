package com.example.Book_project.utils;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

    @Value("${jwt.expiration}")
    private int expiration;

    private static final String SECRET_KEY = "sjgfyurythnvjdsnjvbhgfyurtkjwiowryfhbnvbhfhgioyuikjxncbhgsdtqwtyrgfyfbrbvrehblijvjdsklbngjlthrhgrv";

    public String generateToken(String userName) {

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

    }


    public boolean validate(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
            // Log success or any relevant information
            System.out.println("Token validation successful for subject: " + claimsJws.getBody().getSubject());
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // Log the error for debugging
            System.err.println("Token validation failed: " + e.getMessage());
            return false;
        }
    }


    public String getUsername(String token) {

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();

    }


    //class ends here
}
