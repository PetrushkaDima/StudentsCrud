package com.example.StudentsCrud.service;

import com.example.StudentsCrud.model.Student;

import java.util.Optional;

public interface StudentService {
    Iterable<Student> getAllStudents();

    void createStudent(Student student);

    void deleteStudentByUniqueNumber(String uniqueNumber);

    Optional<Student> findStudentByUniqueNumber(String uniqueNumber);
}
