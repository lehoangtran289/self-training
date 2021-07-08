package com.vds.demo;

import com.vds.entity.school.School;
import com.vds.entity.school.Student;
import com.vds.service.StudentService;
import com.vds.service.impl.StudentServiceImpl;
import com.vds.util.HibernateUtil;
import org.hibernate.Session;

import java.util.Set;

import static com.vds.util.HibernateUtil.doInJpa;

public class FetchTypeDemo {
    private static final StudentService studentService = StudentServiceImpl.getInstance();

    public static void main(String[] args) {
        System.out.println("=====INIT DATA=====");
        studentService.initData("students");

        System.out.println("=====FETCH DATA=====");
        fetchData();
    }

    public static void fetchData() {
        doInJpa(session -> {
            School school = session.createQuery("from School ", School.class).list().get(0);
            System.out.println(school);

            Set<Student> students = school.getStudents();
            System.out.println(students);

//            students.stream().map(Student::getStudentDetail).forEach(System.out::println);
//            students.stream().map(Student::getBooks).forEach(System.out::println);
//            students.stream().map(Student::getSchool).forEach(System.out::println);
        });
    }

    public static void lazyInitExceptionExample() {
        School school;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            school = session.createQuery("from School ", School.class).list().get(0);
            session.close();
        }
        System.out.println(school.getStudents());
    }
}
