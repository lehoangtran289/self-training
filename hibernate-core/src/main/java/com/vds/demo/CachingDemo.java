package com.vds.demo;

import com.vds.service.StudentService;
import com.vds.service.impl.StudentServiceImpl;

public class CachingDemo {
    private static final StudentService studentService = StudentServiceImpl.getInstance();

    public static void main(String[] args) {
    }
}
