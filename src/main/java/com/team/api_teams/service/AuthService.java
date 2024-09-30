package com.team.api_teams.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Service
public class AuthService {

    @Value("${jwt.secret}")
    private String secret;

    private final BCryptPasswordEncoder passwordEncoder;

    private UserService userService;

    @Autowired
    public AuthService(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String authenticate(String username, String password) {
        String storedPassword = userService.findByUsername(username);

        if (storedPassword != null && passwordEncoder.matches(password, storedPassword)) {
            return generateToken(username);
        } else {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    private String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // expire in 10 hours
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}