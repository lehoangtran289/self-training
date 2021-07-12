package com.vds.demo;

import com.vds.entity.school.School;
import com.vds.entity.school.Student;
import com.vds.service.StudentService;
import com.vds.service.impl.StudentServiceImpl;
import com.vds.util.HibernateUtil;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Session;

import static com.vds.util.HibernateUtil.doInTransaction;

public class CascadeTypeDemo {
    private static final StudentService studentService = StudentServiceImpl.getInstance();

    // Note: set Id Generated strategy to != IDENTITY to observe these following demo(s) clearly
    public static void main(String[] args) {
        switch (2) {
            case 1: typePersist(); break;
            case 2: typeMerge(); break;
            case 3: typeRemove(); break;
            case 4: typeDetach(); break;
            case 5: typeRefresh(); break;
            case 6: typeSaveUpdate(); break;
            case 7: typeLock(); break;
            case 8: typeReplicate(); break;
        }
        HibernateUtil.shutdown();
    }

    private static void typePersist() {
        doInTransaction(session -> {
            Student student1 = new Student("Student 1");
            Student student2 = new Student("Student 2");
            School school = new School("school A", "Ha Noi");
            school.addStudents(student1, student2);

            session.persist(school);
            session.flush();
        });
    }

    private static void typeMerge() {
        doInTransaction(session -> {
            Student student1 = new Student("Student 1");
            Student student2 = new Student("Student 2");
            School school = new School("school A", "Ha Noi");
            school.addStudents(student1, student2);
            session.persist(school);
            session.flush();

            int schoolId = school.getId();
            session.clear();
            School school1 = session.get(School.class, schoolId);
            school1.setSchoolName("School B");
            school1.getStudents().forEach(s -> s.setFullName(s.getFullName() + "-modified"));
            session.merge(school1);
            session.flush();

            System.out.println(session.get(School.class, schoolId));
        });
    }

    private static void typeRemove() {
        doInTransaction(session -> {
            Student student1 = new Student("Student A");
            Student student2 = new Student("Student B");
            School school = new School("school A", "Ha Noi");
            school.addStudents(student1, student2);
            session.persist(school);
            session.flush();

            int schoolId = school.getId();
            School school1 = session.get(School.class, schoolId);
            session.remove(school1);
            session.flush();
        });
    }

    private static void typeDetach() {
        doInTransaction(session -> {
            Student student1 = new Student("Student A");
            Student student2 = new Student("Student B");
            School school = new School("school A", "Ha Noi");
            school.addStudents(student1, student2);
            session.persist(school);
            session.flush();

            System.out.println(session.contains(school) + " " + session.contains(student1));
            session.delete(school);
            System.out.println(session.contains(school) + " " + session.contains(student1));
        });
    }

    private static void typeRefresh() {
        doInTransaction(session -> {
            Student student1 = new Student("Student A");
            Student student2 = new Student("Student B");
            School school = new School("school A", "Ha Noi");
            school.addStudents(student1, student2);
            session.persist(school);
            session.flush();

            school.setSchoolName("Henlo world");
            school.getStudents().forEach(s -> s.setFullName(s.getFullName() + "-modified"));
            session.refresh(school);

            System.out.println(school);     // use debug :D
        });
    }

    private static void typeReplicate() {
        doInTransaction(session -> {

        });
    }

    private static void typeLock() {
        doInTransaction(session -> {
            Student student1 = new Student("Student 1");
            Student student2 = new Student("Student 2");
            School school = new School("school A", "Ha Noi");
            school.addStudents(student1, student2);
            session.persist(school);
            session.flush();

            System.out.println("=====BEFORE LOCK=====");
            System.out.println(session.contains(school) + " " + session.contains(student1));

            session.detach(school);
            session.unwrap(Session.class)
                    .buildLockRequest(new LockOptions(LockMode.NONE))
                    .lock(school);

            System.out.println("=====AFTER LOCK=====");
            System.out.println(session.contains(school) + " " + session.contains(student1));
        });
    }

    private static void typeSaveUpdate() {
//        typePersist();
        typeMerge();
    }
}
