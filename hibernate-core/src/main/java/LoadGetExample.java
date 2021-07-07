import entity.User;
import org.hibernate.Session;
import util.HibernateUtil;

public class LoadGetExample {
    public static void main(String[] args) {
        loadData();

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

    public static void loadData() {
        // transient state
        User user1 = new User("User 1", 21);
        User user2 = new User("User 2", 22);
        User user3 = new User("User 3", 23);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(user1);
            session.save(user2);
            session.save(user3);
            session.getTransaction().commit();
            session.close();
        }
    }
}
