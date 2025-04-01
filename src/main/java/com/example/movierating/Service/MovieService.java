package com.example.movierating.Service;

import com.example.movierating.db.dao.MovieDao;
import com.example.movierating.db.po.Movie;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MovieService {

    @Resource
    private MovieDao movieDao;

    public List<Movie> getMovies(int page, int limit) {
        int offset = (page - 1) * limit;
        return movieDao.queryMovies(offset, limit);
    }

    public Movie getMovieById(int movieId) {
        return movieDao.queryMovieById(movieId);
    }

    public Movie getMovieByTitle(String title) {
        return movieDao.queryMovieByTitle(title);
    }

    public int addMovie(Movie movie) {
        return movieDao.insertMovie(movie);
    }

    public int updateMovie(Movie movie) {
        return movieDao.updateMovie(movie);
    }

    public int deleteMovie(int movieId) {
        return movieDao.deleteMovie(movieId);
    }

    public List<Movie> searchMovies(String keyword, int page, int limit) {
        List<Movie> allResults = movieDao.searchMovies("%" + keyword.toLowerCase() + "%");
        // Manual pagination implementation
        int start = (page - 1) * limit;
        if (start >= allResults.size()) {
            return Collections.emptyList();
        }
        int end = Math.min(start + limit, allResults.size());
        return allResults.subList(start, end);
    }
}
