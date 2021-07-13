package com.vds.springdatajpa.application;

import com.vds.springdatajpa.config.PersistenceContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TransactionDemo {

    public static void main(String[] args) {

        switch (1) {
            case 1: demo(); break;
        }
    }

    public static void demo() {
        ApplicationContext context = new AnnotationConfigApplicationContext(PersistenceContext.class);
    }
}
