package com.example.movierating.db.mappers;

import com.example.movierating.db.po.UserRelationship;

public interface UserRelationshipMapper {
    int deleteByPrimaryKey(Integer relationshipId);

    int insert(UserRelationship record);

    int insertSelective(UserRelationship record);

    UserRelationship selectByPrimaryKey(Integer relationshipId);

    int updateByPrimaryKeySelective(UserRelationship record);

    int updateByPrimaryKey(UserRelationship record);

    int insertRelationship(UserRelationship relationship);

    boolean relationshipExists(Integer followerId, Integer followedId);
}