package com.vds.demo;

import com.vds.entity.school.School;
import com.vds.service.StudentService;
import com.vds.service.impl.StudentServiceImpl;
import com.vds.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.stat.Statistics;

import static com.vds.util.HibernateUtil.doInTransaction;
import static com.vds.util.HibernateUtil.getSessionFactory;

public class CachingDemo {
    private static final StudentService studentService = StudentServiceImpl.getInstance();

    public static void main(String[] args) {
        switch (2) {
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
        School school = session1.get(School.class, 1);
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
        studentService.initData("normal");
        HibernateUtil.getSessionFactory().getCache().evictAll();

        Statistics stats = HibernateUtil.getSessionFactory().getStatistics();
        stats.setStatisticsEnabled(true);
        Session session1 = HibernateUtil.getSessionFactory().openSession();
        Session session2 = HibernateUtil.getSessionFactory().openSession();
        session1.beginTransaction();
        session2.beginTransaction();

        printStats(stats);

        System.out.println("--- 1 Hit db---");
        School school = session1.get(School.class, 1);
        System.out.println("school: " + school.getSchoolName());
        printStats(stats);

        System.out.println("--- 2 L1 cache---");
        School school2 = session1.get(School.class, 1);
        System.out.println("school2: " + school2.getSchoolName());
        printStats(stats);

        System.out.println("--- 3 L2 cache---");
        School school3 = session2.get(School.class, 1);
        System.out.println("school3: " + school3.getSchoolName());
        printStats(stats);

        System.out.println("--- 4 L1 cache---");
        School school4 = session2.get(School.class, 1);
        System.out.println("school4: " + school4.getSchoolName());
        printStats(stats);

        session1.close();
        session2.close();
    }

    private static void printStats(Statistics statistics) {
        System.out.println("Second Level Hit Count = " + statistics.getSecondLevelCacheHitCount());
        System.out.println("Second Level Put Count = " + statistics.getSecondLevelCachePutCount());
    }
}
