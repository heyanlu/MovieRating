package com.example.movierating.db.dao.impl;

import com.example.movierating.db.dao.MovieDao;
import com.example.movierating.db.mappers.MovieMapper;
import com.example.movierating.db.po.Movie;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieDaoImpl implements MovieDao {

    @Resource
    private MovieMapper movieMapper;

    @Override
    public List<Movie> queryMovies(int offset, int limit) {
        return movieMapper.selectMovies(offset, limit);
    }

    @Override
    public Movie queryMovieById(Integer movieId) {
        return movieMapper.selectByPrimaryKey(movieId);
    }

    @Override
    public Movie queryMovieByTitle(String title) {
        return movieMapper.selectByTitle(title);
    }

    @Override
    public int insertMovie(Movie movie) {
        return movieMapper.insert(movie);
    }

    @Override
    public int updateMovie(Movie movie) {
        return movieMapper.updateByPrimaryKey(movie);
    }

    @Override
    public int deleteMovie(Integer movieId) {
        return movieMapper.deleteByPrimaryKey(movieId);
    }

    @Override
    public List<Movie> searchMovies(String keywordPattern) {
        return movieMapper.searchMovies(keywordPattern);
    }
}
