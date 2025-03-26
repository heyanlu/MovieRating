package com.example.movierating.controller;

import com.example.movierating.Service.MovieService;
import com.example.movierating.db.po.Movie;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Resource
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        List<Movie> movies = movieService.getMovies(page, limit);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Integer movieId) {
        Movie movie = movieService.getMovieById(movieId);
        if (movie == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movie);
    }

    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        int result = movieService.addMovie(movie);
        if (result > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body(movie);
        }
        return ResponseEntity.internalServerError().build();
    }

    @PutMapping("/{movieId}")
    public ResponseEntity<Movie> updateMovie(
            @PathVariable Integer movieId,
            @RequestBody Movie movie) {
        movie.setMovieId(movieId);
        int result = movieService.updateMovie(movie);
        if (result > 0) {
            return ResponseEntity.ok(movie);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Integer movieId) {
        int result = movieService.deleteMovie(movieId);
        if (result > 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}