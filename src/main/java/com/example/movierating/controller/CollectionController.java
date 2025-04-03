package com.example.movierating.controller;

import com.example.movierating.Service.CollectionService;
import com.example.movierating.db.po.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/collections")
public class CollectionController {
    @Autowired
    private CollectionService collectionService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Collection>> getUserCollection(@PathVariable("userId") Integer userId) {
        List<Collection> collections = collectionService.getUserCollection(userId);
        if (collections == null || collections.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(collections, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<Collection> addToCollection(@RequestBody Map<String, Object> request) {
        Integer userId = (Integer) request.get("user_id");
        Integer movieId = (Integer) request.get("movie_id");

        if (userId == null || movieId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Collection collection = collectionService.addMovieToUserCollection(userId, movieId);
        return new ResponseEntity<>(collection, HttpStatus.CREATED);
    }


    @GetMapping("/user/{userId}/movie/{movieId}")
    public ResponseEntity<Boolean> hasUserCollectedMovie(
        @PathVariable("userId") Integer userId,
        @PathVariable("movieId") Integer movieId) {
        boolean isCollected = collectionService.hasUserCollectedMovie(userId, movieId);
        return new ResponseEntity<>(isCollected, HttpStatus.OK);
    }


    @DeleteMapping("/user/{userId}/movie/{movieId}")
    public ResponseEntity<Map<String, Object>> removeMovieFromCollection(
        @PathVariable("userId") Integer userId,
        @PathVariable("movieId") Integer movieId) {
        boolean success = collectionService.removeMovieFromCollection(userId, movieId);
        Map<String, Object> response = new HashMap<>();
        response.put("success", success);
        return new ResponseEntity<>(response, success ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}