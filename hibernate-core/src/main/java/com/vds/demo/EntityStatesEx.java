package com.vds.demo;

import com.vds.entity.user.User;
import com.vds.entity.user.User2;
import com.vds.service.UserService;
import com.vds.service.impl.UserServiceImpl;
import org.hibernate.Session;
import com.vds.util.HibernateUtil;

public class EntityStatesEx {
    private static final UserService userService = UserServiceImpl.getInstance();

    public static void main(String[] args) {
        switch (4) {
            case 1: persistenceStateExample(); break;
            case 2: removedStateExample(); break;
            case 3: detachedStateExample(); break;
            case 4: updateByIdExample(1); break;
        }
        HibernateUtil.shutdown();
    }

    public static void detachedStateExample() {
        // transient state
        User user1 = new User("User 1", 20);
        User user2 = new User("User 2", 21);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            // persistence state
            session.save(user1);
            session.save(user2);

            // no commit found
            session.close();
        }
        System.out.println(userService.getAllUsers());
    }

    public static void removedStateExample() {
        // transient state
        User user1 = new User("User 1", 21);
        User user2 = new User("User 2", 22);
        User user3 = new User("User 3", 23);
        User2 user4 = new User2("User 4", 24);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            // persistence state
            session.save(user1);
            session.save(user2);
            System.out.println(session.getIdentifier(user1));

            // Removed state
            session.remove(user2);
            session.save(user3);
            session.save(user4);

            session.getTransaction().commit();
            session.close();
        }
        System.out.println(userService.getAllUsers());
        System.out.println(userService.getAllUsers2());
    }

    public static void persistenceStateExample() {
        // transient state
        User user1 = new User("User 1", 18);
        User user2 = new User("User 2", 19);
        User user3 = new User("User 3", 28);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            // persistence state
            session.save(user1);
            session.save(user2);
            session.save(user3);
            session.getTransaction().commit();
            session.close();
        }
        System.out.println(userService.getAllUsers());
    }

    public static void updateByIdExample(int id) {
        userService.initData("user");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            System.out.println("Before -> " + user);

            user.setAge(100);
            session.getTransaction().commit();
            System.out.println("After -> " + session.get(User.class, id));
            session.close();
        }
    }
}
