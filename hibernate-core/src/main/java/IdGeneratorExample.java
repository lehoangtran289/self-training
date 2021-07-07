import entity.User;
import entity.User2;
import org.hibernate.Session;
import util.HibernateUtil;

public class IdGeneratorExample {
    public static void main(String[] args) {
        User user1 = new User("User 11", 211);
        User user2 = new User("User 22", 222);
        User2 user3 = new User2("User 33", 233);
        User2 user4 = new User2("User 44", 244);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(user1);
            session.save(user2);
            session.save(user3);
            session.save(user4);
            session.getTransaction().commit();
            session.close();
        }
        getUserList();
        HibernateUtil.shutdown();
    }

    public static void getUserList() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            System.out.println(session.createQuery("from User ", User.class).list());
            System.out.println(session.createQuery("from User2 ", User2.class).list());
            session.close();
        }
    }
}
