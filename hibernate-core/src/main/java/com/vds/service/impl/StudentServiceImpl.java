package com.vds.service.impl;

import com.vds.entity.school.Book;
import com.vds.entity.school.School;
import com.vds.entity.school.Student;
import com.vds.entity.school.StudentDetail;
import com.vds.service.StudentService;
import com.vds.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private StudentServiceImpl() {
    }

    public static StudentServiceImpl getInstance() {
        return StudentServiceImplHelper.INSTANCE;
    }

    private static class StudentServiceImplHelper {
        private static final StudentServiceImpl INSTANCE = new StudentServiceImpl();
    }

    @Override
    public void initData(String option) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            switch (option) {
                case "students": {
                    Student student1 = new Student("Student 1");
                    Student student2 = new Student("Student 2");
                    School school = new School("school A", "Ha Noi");

                    school.addStudents(student1, student2);

                    session.save(school);
                    break;
                }
                case "normal": {
                    Book book1 = new Book("Physics1");
                    Book book2 = new Book("Math1");
                    Book book3 = new Book("Math2");
                    StudentDetail studentDetail1 = new StudentDetail("a@a.com", "New York");
                    StudentDetail studentDetail2 = new StudentDetail("b@b.com", "HCM");
                    Student student1 = new Student("Student 1");
                    Student student2 = new Student("Student 2");
                    School school = new School("school A", "Ha Noi");

                    student1.setStudentDetail(studentDetail1);
                    student2.setStudentDetail(studentDetail2);
                    student1.addBooks(book1, book2);
                    student2.addBooks(book3);
                    school.addStudents(student1, student2);

                    session.save(school);
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
    public List<Student> getAllStudents() {
        List<Student> ret;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            ret = session.createQuery("from Student ", Student.class).list();
            session.close();
        }
        return ret;
    }

    @Override
    public Student getStudentById(int id) {
        Student ret;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            ret = session.get(Student.class, id);
            session.close();
        }
        return ret;
    }
}
