import entity.User;
import org.hibernate.Session;
import util.HibernateUtil;

public class ObjectIdentityScopeExample {

    public static void main(String[] args) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            User user1 = new User("User 1", 18);
            session.save(user1);
            session.getTransaction().commit();
            session.close();
        }

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            User user2 = session.get(User.class, 1);
            User user3 = session.get(User.class, 1);
            System.out.println(user2 == user3);     // persistence -> true
            session.getTransaction().commit();
            session.close();

            try(Session session2 = HibernateUtil.getSessionFactory().openSession()) {
                session2.beginTransaction();
                User user4 = session2.get(User.class, 1);
                System.out.println(user2 == user4);     // persistence == detached -> false
                session2.close();
            }
        }
    }
}
