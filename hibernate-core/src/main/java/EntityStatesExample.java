import entity.User;
import entity.User2;
import org.hibernate.Session;
import util.HibernateUtil;

public class EntityStatesExample {

    public static void main(String[] args) {
//        persistenceExample();
        removedExample();
//        detachedExample();

        HibernateUtil.shutdown();
    }

    public static void detachedExample() {
        // transient state
        User user1 = new User("User 1", 20);
        User user2 = new User("User 2", 21);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            // persistence state
            session.save(user1);
            session.save(user2);

//            session.getTransaction().commit();
            session.close();
        }
        getUserList();
    }

    public static void removedExample() {
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
        getUserList();
    }

    public static void persistenceExample() {
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
        getUserList();
    }

    public static void updateByIdExample() {
        // Third unit
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            User user = session.get(User.class, 1);
            System.out.println("Before -> " + user);

            user.setAge(100);
            session.getTransaction().commit();
            System.out.println("After -> " + session.get(User.class, 1));
            session.close();
        }
    }

    public static void getUserList() {
        System.out.println("User List:");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            System.out.println(session.createQuery("from User ", User.class).list());
            System.out.println(session.createQuery("from User2 ", User2.class).list());
            session.close();
        }
    }
}
