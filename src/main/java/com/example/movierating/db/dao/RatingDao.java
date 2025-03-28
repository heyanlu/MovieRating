package com.example.movierating.db.dao;

import com.example.movierating.db.po.Rating;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingDao {

    List<Rating> findByMovieId(Integer movieId);

    /**
     * Insert a new rating
     * @param rating Rating entity to insert
     * @return Number of rows affected
     */
    int insert(Rating rating);

    /**
     * Update an existing rating
     * @param rating Rating entity with updated values
     * @return Number of rows affected
     */
    int update(Rating rating);

    /**
     * 获取电影的平均评分
     * @param movieId 电影ID
     * @return 电影的平均评分
     */
    Rating findByUserIdAndMovieId(Integer userId, Integer movieId);
    Double getMovieAvgRating(Integer movieId);
}