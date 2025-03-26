package com.example.movierating.controller;

import com.example.movierating.Service.MovieService;
import com.example.movierating.db.po.Movie;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/movies")
public class MovieController {

    @Resource
    private MovieService movieService;

    // This will serve the HTML page with Thymeleaf
    @GetMapping("/view")
    public String getMoviesView(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "12") int limit,
            Model model) {
        List<Movie> movies = movieService.getMovies(page, limit);
        model.addAttribute("movies", movies);
        model.addAttribute("currentPage", page);
        model.addAttribute("limit", limit);
        return "movies"; // This corresponds to movies.html in templates folder
    }

    // Keep your API endpoints but change their path to /api/movies
    @GetMapping("/api/list")
    @ResponseBody
    public ResponseEntity<List<Movie>> getAllMovies(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "12") int limit) {
        List<Movie> movies = movieService.getMovies(page, limit);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/api/{movieId}")
    @ResponseBody
    public ResponseEntity<Movie> getMovieById(@PathVariable Integer movieId) {
        Movie movie = movieService.getMovieById(movieId);
        if (movie == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movie);
    }

    @PostMapping("/api/addMovie")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        int result = movieService.addMovie(movie);
        if (result > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body(movie);
        }
        return ResponseEntity.internalServerError().build();
    }

    @PutMapping("/api/{movieId}")
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

    @DeleteMapping("/api/{movieId}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Integer movieId) {
        int result = movieService.deleteMovie(movieId);
        if (result > 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/api/search")
    public ResponseEntity<List<Movie>> searchMovies(
            @RequestParam String query,
            @RequestParam(defaultValue = "10") int limit) {
        List<Movie> movies = movieService.searchMovies(query);
        if (movies.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(movies.stream().limit(limit).collect(Collectors.toList()));
    }
}