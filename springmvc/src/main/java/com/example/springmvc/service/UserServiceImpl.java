package com.example.springmvc.service;

import com.example.springmvc.dao.UserDao;
import com.example.springmvc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public void save(User user) {
        userDao.save(user);
    }

    @Transactional
    public List<User> list() {
        return userDao.list();
    }
}
