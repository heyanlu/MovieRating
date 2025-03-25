package com.example.movierating.db.dao.impl;

import com.example.movierating.db.dao.UserDao;
import com.example.movierating.db.po.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;


@SpringBootTest
class UserDaoImplTest {

    @Resource
    private UserDao userDao;


    private User testUser;

    @BeforeEach
    void setUp() {

    }

    @Test
    void insertUser_ShouldSuccessfullyInsertUserAndReturnUserId() {
        // Arrange
        User testUser = User.builder()
                .userId(null) // ID should be null before insertion
                .username("testuser")
                .email("test@example.com")
                .password("hashedpassword")
                .profileUrl("http://example.com/profile.jpg")
                .bio("Test bio")
                .createDate(java.sql.Timestamp.valueOf(LocalDateTime.now()))
                .updateDate(java.sql.Timestamp.valueOf(LocalDateTime.now()))
                .build();

        userDao.insertUser(testUser);
    }



}