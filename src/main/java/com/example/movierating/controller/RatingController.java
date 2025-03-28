package com.example.movierating.controller;

import com.example.movierating.Service.RatingService;
import com.example.movierating.db.po.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    /**
     * 添加或更新评分
     * API: POST /api/ratings/rate
     */
    @PostMapping("/rate")
    public ResponseEntity<Rating> addOrUpdateRating(@RequestBody Rating rating) {
        Rating savedRating = ratingService.addOrUpdateRating(rating);
        return ResponseEntity.ok(savedRating);
    }

//    /**
//     * 获取用户对电影的评分
//     * API: GET /api/ratings/user/{userId}/movie/{movieId}
//     */
//    @GetMapping("/user/{userId}/movie/{movieId}")
//    public ResponseEntity<Rating> getRatingByUserAndMovie(
//        @PathVariable Integer userId,
//        @PathVariable Integer movieId) {
//        Rating rating = ratingService.getRatingByUserAndMovie(userId, movieId);
//        if (rating == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(rating);
//    }
    /**
     * 获取电影的平均评分
     * API: GET /api/ratings/movie/{movieId}/avg
     */
    @GetMapping("/movie/{movieId}/avg")
    public ResponseEntity<Double> getMovieAvgRating(@PathVariable Integer movieId) {
        // 使用 ratingService 来计算或获取电影的平均评分
        Double avgRating = ratingService.getMovieAvgRating(movieId);
        if (avgRating == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(avgRating);
    }


}