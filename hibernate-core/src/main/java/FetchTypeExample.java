import org.hibernate.Session;
import util.HibernateUtil;

public class FetchTypeExample {

    public static void main(String[] args) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            session.close();
        }
    }
}
