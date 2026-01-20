package com.sromey.project.repository;

import com.sromey.project.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,String> {
    @Query(name = "Student.findStudentWithEnrollment")
    List<Student> fetchAllStudents();

    Optional<Student> findByRollNo(String rollNo);
}
