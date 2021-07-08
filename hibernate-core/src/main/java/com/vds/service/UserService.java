package com.vds.service;

import com.vds.entity.user.User;
import com.vds.entity.user.User2;

import java.util.List;

public interface UserService {
    void initData(String seed);

    List<User> getAllUsers();

    List<User2> getAllUsers2();

    User getUserById(int id);
}
