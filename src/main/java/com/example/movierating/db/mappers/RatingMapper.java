package com.example.movierating.db.mappers;

import com.example.movierating.db.po.Rating;

public interface RatingMapper {
    int deleteByPrimaryKey(Integer ratingId);

    int insert(Rating record);

    int insertSelective(Rating record);

    Rating selectByPrimaryKey(Integer ratingId);

    int updateByPrimaryKeySelective(Rating record);

    int updateByPrimaryKeyWithBLOBs(Rating record);

    int updateByPrimaryKey(Rating record);
}