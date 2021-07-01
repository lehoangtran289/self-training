package com.example.springmvc.service;

import com.example.springmvc.entity.User;

import java.util.List;

public interface UserService {
    void save(User user);

    List<User> list();
}
