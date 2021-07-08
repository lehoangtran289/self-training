package com.vds.demo;

import com.vds.entity.user.User;
import com.vds.service.UserService;
import com.vds.service.impl.UserServiceImpl;
import org.hibernate.Session;
import com.vds.util.HibernateUtil;

public class ObjectIdentityScopeEx {
    private static final UserService userService = UserServiceImpl.getInstance();

    public static void main(String[] args) {
        userService.initData("user");

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            User user2 = session.get(User.class, 1);
            User user3 = session.get(User.class, 1);
            System.out.println("2 persistence instances -> " + (user2 == user3));     // persistence -> true
            session.getTransaction().commit();
            session.close();

            try(Session session2 = HibernateUtil.getSessionFactory().openSession()) {
                session2.beginTransaction();
                User user4 = session2.get(User.class, 1);
                System.out.println("1 persistence & 1 detached instance -> " + (user2 == user4));     // persistence == detached -> false
                session2.close();
            }
        }
    }
}
