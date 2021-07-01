package com.example.springmvc.dao;

import com.example.springmvc.entity.User;

import java.util.List;

public interface UserDao {
    void save(User user);
    List<User> list();
}
