package com.vds.service;

import com.vds.entity.school.Student;

import java.util.List;

public interface StudentService {
    void initData(String option);

    List<Student> getAllStudents();

    Student getStudentById(int id);
}
