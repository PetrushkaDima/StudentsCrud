package com.example.StudentsCrud.service.impl;

import com.example.StudentsCrud.model.Student;
import com.example.StudentsCrud.repository.StudentRepository;
import com.example.StudentsCrud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Iterable<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void createStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void deleteStudentByUniqueNumber(String uniqueNumber) {
        studentRepository.deleteByUniqueNumber(uniqueNumber);
    }

    @Override
    public Optional<Student> findStudentByUniqueNumber(String uniqueNumber) {
        return studentRepository.findByUniqueNumber(uniqueNumber);
    }
}
