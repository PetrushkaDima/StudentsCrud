package com.example.StudentsCrud.repository;

import com.example.StudentsCrud.model.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, Long> {
    void deleteByUniqueNumber(String uniqueNumber);
    Optional<Student> findByUniqueNumber(String uniqueNumber);
}
