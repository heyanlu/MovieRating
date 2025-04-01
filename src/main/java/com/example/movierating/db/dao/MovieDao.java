package com.example.movierating.db.dao;

import com.example.movierating.db.po.Movie;

import java.util.List;

public interface MovieDao {
    List<Movie> queryMovies(int offset, int limit);

    Movie queryMovieById(Integer movieId);

    Movie queryMovieByTitle(String title);

    int insertMovie(Movie movie);

    int updateMovie(Movie movie);

    int deleteMovie(Integer movieId);

    List<Movie> searchMovies(String keywordPattern);

}
