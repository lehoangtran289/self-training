package com.vds.service.impl;

import com.vds.entity.user.User;
import com.vds.entity.user.User2;
import com.vds.service.UserService;
import com.vds.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

import static com.vds.util.HibernateUtil.doInJpa;

public class UserServiceImpl implements UserService {
    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return UserServiceImpl.UserServiceImplHelper.INSTANCE;
    }

    private static class UserServiceImplHelper {
        private static final UserServiceImpl INSTANCE = new UserServiceImpl();
    }

    @Override
    public void initData(String option) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            switch (option) {
                case "user": {
                    User user1 = new User("User 1", 21);
                    User user2 = new User("User 2", 22);
                    User user3 = new User("User 3", 23);

                    session.save(user1);
                    session.save(user2);
                    session.save(user3);
                    break;
                }
                case "bothUser": {
                    User user1 = new User("User 11", 211);
                    User user2 = new User("User 22", 222);
                    User2 user3 = new User2("User 33", 233);
                    User2 user4 = new User2("User 44", 244);

                    session.save(user1);
                    session.save(user2);
                    session.save(user3);
                    session.save(user4);
                    break;
                }
                default:
                    System.out.println("Option not found");
                    break;
            }
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> ret = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            ret = session.createQuery("from User ", User.class).list();
            session.close();
        }
        return ret;
    }

    public List<User2> getAllUsers2() {
        List<User2> ret;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            ret = session.createQuery("from User2 ", User2.class).list();
            session.close();
        }
        return ret;
    }

    @Override
    public User getUserById(int id) {
        User ret;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            ret = session.get(User.class, id);
            session.close();
        }
        return ret;
    }
}
