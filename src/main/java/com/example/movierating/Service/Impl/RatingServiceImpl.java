package com.example.movierating.Service.Impl;

import com.example.movierating.Service.RatingService;
import com.example.movierating.db.dao.RatingDao;
import com.example.movierating.db.mappers.RatingMapper;
import com.example.movierating.db.po.Rating;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingDao ratingDao;
    @Autowired
    private RatingMapper ratingMapper;

    @Override
    @Transactional
    public Rating addOrUpdateRating(Rating rating) {
        // 设置更新日期
        Date now = new Date();
        rating.setUpdateDate(now);

        // 检查是否已存在该用户对该电影的评分
        Rating existingRating = ratingDao.findByUserIdAndMovieId(rating.getUserId(), rating.getMovieId());

        if (existingRating != null) {
            // 如果存在，则更新
            rating.setRatingId(existingRating.getRatingId());
            ratingDao.update(rating);
        } else {
            // 如果不存在，则插入新记录
            rating.setCreateDate(now);
            ratingDao.insert(rating);
        }

        return rating;
    }
    @Override
    public Double getMovieAvgRating(Integer movieId) {
        // 从数据库中查询该电影的所有评分
        List<Rating> ratings = ratingDao.findByMovieId(movieId);

        // 如果没有评分，返回 null 或默认值
        if (ratings == null || ratings.isEmpty()) {
            return null; // 或者返回 0.0
        }

        // Calculate avg rating
        double sum = 0.0;
        for (Rating rating : ratings) {
            // 将 BigDecimal 转换为 double
            sum += rating.getScore().doubleValue();
        }

        return sum / ratings.size();
    }

    @Override
    public List<Rating> getMovieRatings(Integer movieId) {
        return ratingMapper.findByMovieId(movieId);
    }

}