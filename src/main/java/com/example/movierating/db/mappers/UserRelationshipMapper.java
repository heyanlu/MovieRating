package com.example.movierating.db.mappers;

import com.example.movierating.db.po.User;
import com.example.movierating.db.po.UserRelationship;
import java.util.List;

public interface UserRelationshipMapper {
    int deleteByPrimaryKey(Integer relationshipId);

    int insert(UserRelationship record);

    int insertSelective(UserRelationship record);

    UserRelationship selectByPrimaryKey(Integer relationshipId);

    int updateByPrimaryKeySelective(UserRelationship record);

    int updateByPrimaryKey(UserRelationship record);

    boolean relationshipExists(int followerId, int followedId);

    void insertRelationship(UserRelationship relationship);

    List<User> selectFollowerIdsByUserId(Integer userId);
}