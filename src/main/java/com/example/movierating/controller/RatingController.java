package com.example.movierating.controller;

import com.example.movierating.Service.RatingService;
import com.example.movierating.db.po.Rating;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
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
//    @PostMapping("/rate")
//    public ResponseEntity<Rating> addOrUpdateRating(@RequestBody Rating rating) {
//        Rating savedRating = ratingService.addOrUpdateRating(rating);
//        return ResponseEntity.ok(savedRating);
//    }

    @PostMapping("/rate")
    public ResponseEntity<Rating> addOrUpdateRating(@RequestBody String jsonBody, HttpServletRequest request) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Rating rating = mapper.readValue(jsonBody, Rating.class);
            Rating savedRating = ratingService.addOrUpdateRating(rating);
            return ResponseEntity.ok(savedRating);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

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

    /**
     * 获取电影的所有评分和评论
     * API: GET /api/ratings/movie/{movieId}
     */
    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<Rating>> getMovieRatings(@PathVariable Integer movieId) {
        List<Rating> ratings = ratingService.getMovieRatings(movieId);
        return ResponseEntity.ok(ratings);
    }


}