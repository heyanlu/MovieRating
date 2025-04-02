package com.example.movierating.controller;

import com.example.movierating.Service.MovieService;
import com.example.movierating.db.po.Movie;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
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
//    public String getMoviesView(
//            @RequestParam(defaultValue = "1") int page,
//            @RequestParam(defaultValue = "12") int limit,
//            Model model) {
//        List<Movie> movies = movieService.getMovies(page, limit);
//        model.addAttribute("movies", movies);
//        model.addAttribute("currentPage", page);
//        model.addAttribute("limit", limit);
//        model.addAttribute("isSearch", false);
//        movies.forEach(movie -> movie.setPosterUrl("/images/green_book.jpg"));
//
//        return "movies";
//    }
    public String getMoviesView(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "12") int limit,
            Model model,
            HttpSession session) {

        if (session.getAttribute("userEmail") == null) {
            return "redirect:/";
        }

        List<Movie> movies = movieService.getMovies(page, limit);
        model.addAttribute("movies", movies);
        model.addAttribute("currentPage", page);
        model.addAttribute("limit", limit);
        model.addAttribute("isSearch", false);

        model.addAttribute("username", session.getAttribute("username"));

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



//    @GetMapping("/movies/{title}")
//    public String getMovieDetails(@PathVariable String title,
//                                  Model model,
//                                  HttpSession session) {
//
//        if (session.getAttribute("userEmail") == null) {
//            return "redirect:/login";
//        }
//
//        Movie movie = movieService.getMovieByTitle(title);
//        System.out.println("Retrieved movie: " + (movie != null ? movie.getTitle() : "null"));
//
//        model.addAttribute("movie", movie);
//        model.addAttribute("username", session.getAttribute("username"));
//
//        return "movie-detail";
//    }
    @GetMapping("/movies/{id}")
    public String getMovieDetails(@PathVariable Integer id,
        Model model,
        HttpSession session) {

        if (session.getAttribute("userEmail") == null) {
            return "redirect:/login";
        }

        Movie movie = movieService.getMovieById(id);
        System.out.println("Retrieved movie: " + (movie != null ? movie.getTitle() : "null"));

        if (movie != null) {
            // 确保 movie 对象有 movieId
            System.out.println("Movie ID: " + movie.getMovieId());
        }

        model.addAttribute("movie", movie);
        model.addAttribute("username", session.getAttribute("username"));

        // 将用户ID添加到模型中，以便在JavaScript中使用
        model.addAttribute("userId", session.getAttribute("userId"));

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