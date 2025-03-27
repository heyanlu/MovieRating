package com.example.movierating.controller;

import com.example.movierating.Service.RelationshipService;
import com.example.movierating.Service.UserService;
import com.example.movierating.db.po.User;
import com.example.movierating.db.po.UserRelationship;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private RelationshipService relationshipService;


    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        User user = userService.login(email, password);
        if (user == null) {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }

        session.setAttribute("user", user);
        session.setAttribute("userId", user.getUserId());
        session.setAttribute("userEmail", user.getEmail());
        session.setAttribute("username", user.getUsername());

        return "redirect:/movies";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("username", "");
        model.addAttribute("email", "");
        model.addAttribute("profileUrl", "");
        model.addAttribute("bio", "");
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam(required = false) String profileUrl,
            @RequestParam(required = false) String bio,
            HttpSession session,  // Add HttpSession parameter
            Model model) {

        try {
            User user = userService.createUser(username, email, password, profileUrl, bio);
            if (user == null) {
                model.addAttribute("error", "Username or email already exists");
                return "register";
            }

            session.setAttribute("userEmail", user.getEmail());
            session.setAttribute("username", user.getUsername());

            return "redirect:/movies";

        } catch (Exception e) {
            model.addAttribute("error", "Registration failed");
            return "register";
        }
    }

    @GetMapping("/profile")
    public String showProfile(HttpSession session, Model model) {
        String userEmail = (String) session.getAttribute("userEmail");
        Integer userId = (Integer) session.getAttribute("userId");

        if (userEmail == null) {
            return "redirect:/login";
        }

        User user = userService.getUserByEmail(userEmail);

        if (user == null) {
            return "redirect:/login";
        }

        List<User> followers = relationshipService.getFollowerIds(userId);
        List<User> following = relationshipService.getFollowers(userId);

        model.addAttribute("user", user);
        model.addAttribute("followers", followers);
        model.addAttribute("following", following);
        model.addAttribute("followerCount", followers.size());
        model.addAttribute("followingCount", following.size());

        return "profile";
    }


    @PostMapping("/profile/follow")
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

    @GetMapping("/profile/followers")
    public ResponseEntity<List<User>> getFollowers(@PathVariable Integer userId) {
        try {
            List<User> followers = relationshipService.getFollowerIds(userId);
            return ResponseEntity.ok(followers);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}