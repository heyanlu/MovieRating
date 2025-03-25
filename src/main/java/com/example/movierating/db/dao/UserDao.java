package com.example.movierating.db.dao;

import com.example.movierating.db.po.User;

public interface UserDao {
    int insertUser(User user);
    User selectUserByUsername(String username);
    User selectUserByEmail(String email);
}