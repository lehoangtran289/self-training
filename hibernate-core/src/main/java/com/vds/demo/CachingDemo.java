package com.vds.demo;

import com.vds.entity.school.School;
import com.vds.service.StudentService;
import com.vds.service.impl.StudentServiceImpl;
import com.vds.util.HibernateUtil;
import org.hibernate.Session;

import static com.vds.util.HibernateUtil.doInTransaction;

public class CachingDemo {
    private static final StudentService studentService = StudentServiceImpl.getInstance();

    public static void main(String[] args) {
        switch (1) {
            case 1: firstLevelCaching(); break;
            case 2: secondLevelCaching(); break;
        }
        HibernateUtil.shutdown();
    }

    private static void firstLevelCaching() {
        studentService.initData("normal");

        Session session1 = HibernateUtil.getSessionFactory().openSession();
        Session session2 = HibernateUtil.getSessionFactory().openSession();
        session1.beginTransaction();
        session2.beginTransaction();

        System.out.println("=====SESSION 1=====");
        School school = session1.load(School.class, 1);
        System.out.println(school.getSchoolName());
        for(int i = 0; i < 3; i++) {
            school = session1.load(School.class, 1);
            System.out.println(school.getSchoolName());
        }
        session1.getTransaction().commit();

        System.out.println("=====SESSION 1 (diff transaction)=====");
        session1.beginTransaction();
        school = session1.load(School.class, 1);
        System.out.println(school.getSchoolName());
        session1.getTransaction().commit();

        System.out.println("=====SESSION 2=====");
        school = session2.load(School.class, 1);
        System.out.println(school.getSchoolName());

        System.out.println("=====SESSION 2 (evict test)=====");
        session2.evict(school);
        // session2.clear();
        school = session2.load(School.class, 1);
        System.out.println(school.getSchoolName());

        session1.close();
        session2.close();
    }

    private static void secondLevelCaching() {

    }
}
