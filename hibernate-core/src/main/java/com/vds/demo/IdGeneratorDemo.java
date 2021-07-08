package com.vds.demo;

import com.vds.service.UserService;
import com.vds.service.impl.UserServiceImpl;
import com.vds.util.HibernateUtil;

public class IdGeneratorDemo {
    private static final UserService userService = UserServiceImpl.getInstance();

    public static void main(String[] args) {
        userService.initData("bothUser");
        System.out.println(userService.getAllUsers());
        System.out.println(userService.getAllUsers2());
        HibernateUtil.shutdown();
    }
}
