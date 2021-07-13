package com.vds.demo;

import com.vds.entity.user.User;
import com.vds.entity.user.User2;
import com.vds.service.UserService;
import com.vds.service.impl.UserServiceImpl;
import com.vds.util.HibernateUtil;

import java.util.concurrent.TimeUnit;

import static com.vds.util.HibernateUtil.doInTransaction;

public class EntityStatesDemo {
    private static final UserService userService = UserServiceImpl.getInstance();

    public static void main(String[] args) {
        switch (1) {
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

        doInTransaction(session -> {
            // persistence state
            session.save(user1);
            session.save(user2);

            // no commit found
        });
        System.out.println(userService.getAllUsers());
    }

    public static void removedStateExample() {
        // transient state
        User user1 = new User("User 1", 21);
        User user2 = new User("User 2", 22);
        User user3 = new User("User 3", 23);
        User2 user4 = new User2("User 4", 24);

        doInTransaction(session -> {
            // persistence state
            session.save(user1);
            session.save(user2);
            System.out.println(session.getIdentifier(user1));

            // Removed state
            session.remove(user2);
            session.save(user3);
            session.save(user4);

            session.getTransaction().commit();
        });
        System.out.println(userService.getAllUsers());
        System.out.println(userService.getAllUsers2());
    }

    public static void persistenceStateExample() {
        Thread thread1 = new Thread(() -> {
            // transient state
            User user1 = new User("User 1", 18);

            doInTransaction(session -> {
                // persistence state
                session.save(user1);
//                session.getTransaction().commit();    // enable hibernate.autocommit for autocommit mode
            });
        });
        Thread thread2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            doInTransaction(session -> {
                System.out.println(session.get(User.class, 1));
            });
        });
        thread1.start();
        thread2.start();
    }

    public static void updateByIdExample(int id) {
        userService.initData("user");

        doInTransaction(session -> {
            User user = session.get(User.class, id);
            System.out.println("Before -> " + user);
            user.setAge(100);

            session.flush();
            user.setAge(102);
//            session.refresh(user);

            System.out.println(user.getAge());

//            session.getTransaction().commit();
        });

        doInTransaction(session -> {
            System.out.println("After -> " + session.get(User.class, id));
        });
    }
}
