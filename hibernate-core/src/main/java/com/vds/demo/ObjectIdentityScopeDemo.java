package com.vds.demo;

import com.vds.entity.user.User;
import com.vds.service.UserService;
import com.vds.service.impl.UserServiceImpl;

import static com.vds.util.HibernateUtil.doInTransaction;

public class ObjectIdentityScopeDemo {
    private static final UserService userService = UserServiceImpl.getInstance();

    public static void main(String[] args) {
        userService.initData("user");

        doInTransaction(session -> {
            User user2 = session.get(User.class, 1);
            User user3 = session.get(User.class, 1);
            System.out.println("2 persistence instances -> " + (user2 == user3));     // persistence -> true
            session.getTransaction().commit();
            session.close();

            doInTransaction(session2 -> {
                User user4 = session2.get(User.class, 1);
                System.out.println("1 persistence & 1 detached instance -> " + (user2 == user4));     // persistence == detached -> false
            });
        });
    }
}
