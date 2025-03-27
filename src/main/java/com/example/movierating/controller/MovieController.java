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

@Controller
public class MovieController {

    @Resource
    private MovieService movieService;

    /* ============== Thymeleaf View Endpoints ============== */

    @GetMapping("/movies")
    public String getMoviesView(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "12") int limit,
            Model model) {
        List<Movie> movies = movieService.getMovies(page, limit);
        model.addAttribute("movies", movies);
        model.addAttribute("currentPage", page);
        model.addAttribute("limit", limit);
        model.addAttribute("isSearch", false);
        movies.forEach(movie -> movie.setPosterUrl("/images/green_book.jpg"));

        return "movies";
    }

    @GetMapping("/movies/search")
    public String searchMoviesView(
            @RequestParam String query,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "12") int limit,
            Model model) {
        List<Movie> movies = movieService.searchMovies(query, page, limit);
        model.addAttribute("movies", movies);
        model.addAttribute("currentPage", page);
        model.addAttribute("limit", limit);
        model.addAttribute("searchQuery", query);
        model.addAttribute("isSearch", true);
        movies.forEach(movie -> movie.setPosterUrl("/images/green_book.jpg"));

        return "movies";
    }



    @GetMapping("/movies/{movieId}")
    public String getMovieDetails(@PathVariable Integer movieId, Model model) {
        Movie movie = movieService.getMovieById(movieId);
        if (movie == null) {
            return "error";
        }
        model.addAttribute("movie", movie);
        return "movie-detail";
    }



    /* ============== Existing API Endpoints has not connect with frontend  ============== */

    @PostMapping("/movies/addMovie")
    @ResponseBody
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        int result = movieService.addMovie(movie);
        if (result > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body(movie);
        }
        return ResponseEntity.internalServerError().build();
    }

    @PutMapping("/movies/{movieId}")
    @ResponseBody
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

    @DeleteMapping("/movies/{movieId}")
    @ResponseBody
    public ResponseEntity<Void> deleteMovie(@PathVariable Integer movieId) {
        int result = movieService.deleteMovie(movieId);
        if (result > 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}