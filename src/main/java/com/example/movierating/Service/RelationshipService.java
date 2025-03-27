package com.example.movierating.Service;

import com.example.movierating.db.mappers.UserRelationshipMapper;
import com.example.movierating.db.po.User;
import com.example.movierating.db.po.UserRelationship;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationshipService {

    @Resource
    private UserRelationshipMapper userRelationshipMapper;

    @Resource
    private UserService userService;

    public UserRelationship followUser(int followerId, int followedId) {
        if (userRelationshipMapper.relationshipExists(followerId, followedId)) {
            throw new IllegalArgumentException("Relationship already exists");
        }

        UserRelationship relationship = UserRelationship.builder()
                .followerId(followerId)
                .followedId(followedId)
                .build();

        userRelationshipMapper.insertRelationship(relationship);
        return relationship;
    }

    public List<User> getFollowerIds(Integer userId) {
        return userRelationshipMapper.selectFollowerIdsByUserId(userId);
    }


    public List<User> getFollowers(Integer userId) {
        return userRelationshipMapper.findByFollowerId(userId);
    }


    public boolean unfollow(int followerId, int followedId) {
        if (!userRelationshipMapper.relationshipExists(followerId, followedId)) {
            return false;
        }


        UserRelationship relationship = UserRelationship.builder()
                .followerId(followerId)
                .followedId(followedId)
                .isactive(false)
                .build();

        int rowsAffected = userRelationshipMapper.updateByPrimaryKeySelective(relationship);

        return rowsAffected > 0;
    }


}