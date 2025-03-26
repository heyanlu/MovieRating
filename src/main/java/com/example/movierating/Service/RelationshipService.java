package com.example.movierating.Service;

import com.example.movierating.db.mappers.UserRelationshipMapper;
import com.example.movierating.db.po.UserRelationship;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RelationshipService {

    @Resource
    private UserRelationshipMapper userRelationshipMapper;

    @Transactional
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
}