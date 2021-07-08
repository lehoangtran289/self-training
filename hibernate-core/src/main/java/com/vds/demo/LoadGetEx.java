package com.vds.demo;

import com.vds.entity.user.User;
import com.vds.service.UserService;
import com.vds.service.impl.UserServiceImpl;
import org.hibernate.Session;
import com.vds.util.HibernateUtil;

public class LoadGetEx {
    private static final UserService userService = UserServiceImpl.getInstance();

    public static void main(String[] args) {
        userService.initData("user");

        System.out.println("========HIBERNATE LOAD");
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            User u1 = session.load(User.class, 2);
            System.out.println("get id: " + u1.getId());
            System.out.println("get age: " + u1.getAge());    // call SELECT
            session.close();
        }

        System.out.println("========HIBERNATE GET");
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            User u1 = session.get(User.class, 2);
            System.out.println("get id: " + u1.getId());
            System.out.println("get age: " + u1.getAge());    // call SELECT
            session.close();
        }
    }
}
