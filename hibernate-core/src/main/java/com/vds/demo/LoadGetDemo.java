package com.vds.demo;

import com.vds.entity.user.User;
import com.vds.service.UserService;
import com.vds.service.impl.UserServiceImpl;

import static com.vds.util.HibernateUtil.doInTransaction;

public class LoadGetDemo {
    private static final UserService userService = UserServiceImpl.getInstance();

    public static void main(String[] args) {
        userService.initData("user");

        System.out.println("========HIBERNATE LOAD========");
        doInTransaction(session -> {
            User u1 = session.load(User.class, 2);
            System.out.println("get id: " + u1.getId());
            System.out.println("get age: " + u1.getAge());    // call SELECT
        });

        System.out.println("========HIBERNATE GET========");
        doInTransaction(session -> {
            User u1 = session.get(User.class, 2);
            System.out.println("get id: " + u1.getId());
            System.out.println("get age: " + u1.getAge());    // call SELECT
        });
    }
}
