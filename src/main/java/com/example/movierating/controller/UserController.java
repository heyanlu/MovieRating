package com.example.movierating.controller;

import com.example.movierating.Service.RelationshipService;
import com.example.movierating.Service.UserService;
import com.example.movierating.db.po.User;
import com.example.movierating.db.po.UserRelationship;
import jakarta.annotation.Resource;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private RelationshipService relationshipService;

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam(required = false) String profileUrl,
            @RequestParam(required = false) String bio) {

        try {
            User user = userService.createUser(username, email, password, profileUrl, bio);
            if (user == null) {
                return ResponseEntity.badRequest().body("Username or email already exists");
            }
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Registration failed");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestParam String usernameOrEmail,
            @RequestParam String password) {

        User user = userService.login(usernameOrEmail, password);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid credentials"));
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/token-login")
    public ResponseEntity<?> tokenLogin(
            @RequestParam String usernameOrEmail,
            @RequestParam String password) {

        Map<String, Object> authResult = userService.loginWithToken(usernameOrEmail, password);
        if (authResult == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid credentials"));
        }
        return ResponseEntity.ok(authResult);
    }

    @PostMapping("/{userId}/follow")
    public ResponseEntity<UserRelationship> followUser(
            @PathVariable Integer userId,
            @RequestBody FollowRequest request) {

        try {
            UserRelationship relationship = relationshipService.followUser(
                    request.getFollowerId(),
                    userId);
            return ResponseEntity.status(HttpStatus.CREATED).body(relationship);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @Data
    static class FollowRequest {
        private Integer followerId;
    }

}