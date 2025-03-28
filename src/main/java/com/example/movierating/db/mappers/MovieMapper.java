package com.example.movierating.db.mappers;

import com.example.movierating.db.po.Movie;
import java.util.List;

public interface MovieMapper {
    int deleteByPrimaryKey(Integer movieId);

    int insert(Movie record);

    int insertSelective(Movie record);

    Movie selectByPrimaryKey(Integer movieId);

    int updateByPrimaryKeySelective(Movie record);

    int updateByPrimaryKeyWithBLOBs(Movie record);

    int updateByPrimaryKey(Movie record);

    List<Movie> selectMovies(int offset, int limit);

    List<Movie> searchMovies(String keywordPattern);
}