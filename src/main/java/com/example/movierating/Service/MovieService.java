package com.example.movierating.Service;

import com.example.movierating.db.dao.MovieDao;
import com.example.movierating.db.po.Movie;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public int addMovie(Movie movie) {
        return movieDao.insertMovie(movie);
    }

    public int updateMovie(Movie movie) {
        return movieDao.updateMovie(movie);
    }

    public int deleteMovie(int movieId) {
        return movieDao.deleteMovie(movieId);
    }
}
