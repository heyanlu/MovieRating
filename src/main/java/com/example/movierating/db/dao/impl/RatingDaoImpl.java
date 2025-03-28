package com.example.movierating.db.dao.impl;

import static com.mysql.cj.conf.PropertyKey.logger;

import com.example.movierating.db.dao.RatingDao;
import com.example.movierating.db.po.Rating;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class RatingDaoImpl implements RatingDao {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public int insert(Rating rating) {
        return sqlSession.insert("com.example.movierating.db.mappers.RatingMapper.insert", rating);
    }

    @Override
    public int update(Rating rating) {
        return sqlSession.update("com.example.movierating.db.mappers.RatingMapper.updateByPrimaryKeyWithBLOBs", rating);
    }

    @Override
    public Double getMovieAvgRating(Integer movieId) {
        return sqlSession.selectOne("com.example.movierating.db.mappers.RatingMapper.getMovieAvgRating", movieId);
    }

    @Override
    public List<Rating> findByMovieId(Integer movieId) {
        return sqlSession.selectList("com.example.movierating.db.mappers.RatingMapper.findByMovieId", movieId);
    }
    @Override
    public Rating findByUserIdAndMovieId(Integer userId, Integer movieId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("movieId", movieId);
        return sqlSession.selectOne("com.example.movierating.db.mappers.RatingMapper.findByUserIdAndMovieId", params);
    }

}