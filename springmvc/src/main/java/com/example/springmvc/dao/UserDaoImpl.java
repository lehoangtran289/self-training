package com.example.springmvc.dao;

import com.example.springmvc.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    private static List<User> users;

    static {
        users = new ArrayList<>();
        users.add(new User("a", "a"));
        users.add(new User("b", "b"));
        users.add(new User("c", "c"));
    }

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public List<User> list() {
        return users;
    }
}
