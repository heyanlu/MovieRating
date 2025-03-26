package com.example.movierating.db.mappers;

import com.example.movierating.db.po.MovieActorKey;

public interface MovieActorMapper {
    int deleteByPrimaryKey(MovieActorKey key);

    int insert(MovieActorKey record);

    int insertSelective(MovieActorKey record);
}