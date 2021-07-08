package com.vds.demo;

import com.vds.entity.user.User;
import com.vds.service.UserService;
import com.vds.service.impl.UserServiceImpl;
import org.hibernate.Session;
import com.vds.util.HibernateUtil;

import static com.vds.util.HibernateUtil.doInJpa;

public class LoadGetDemo {
    private static final UserService userService = UserServiceImpl.getInstance();

    public static void main(String[] args) {
        userService.initData("user");

        System.out.println("========HIBERNATE LOAD========");
        doInJpa(session -> {
            User u1 = session.load(User.class, 2);
            System.out.println("get id: " + u1.getId());
            System.out.println("get age: " + u1.getAge());    // call SELECT
        });

        System.out.println("========HIBERNATE GET========");
        doInJpa(session -> {
            User u1 = session.get(User.class, 2);
            System.out.println("get id: " + u1.getId());
            System.out.println("get age: " + u1.getAge());    // call SELECT
        });
    }
}
