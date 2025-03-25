package com.example.movierating.db.dao.impl;

import com.example.movierating.db.dao.UserDao;
import com.example.movierating.db.mappers.UserMapper;
import com.example.movierating.db.po.User;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class UserDaoImpl implements UserDao {

    @Resource
    UserMapper userMapper;

    @Override
    public int insertUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User selectUserByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public User selectUserByEmail(String email) {
        return userMapper.selectByEmail(email);
    }
}
