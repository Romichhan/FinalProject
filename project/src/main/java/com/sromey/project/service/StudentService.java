package com.sromey.project.service;

import com.sromey.project.dto.StudentRequestDTO;

import java.util.List;

public interface StudentService {
    void enrollStudent(StudentRequestDTO studentRequestDTO);

    List<StudentRequestDTO> fetchAllStudents();

    void patchStudent(String id, Object modifyValues);

    StudentRequestDTO fetchByRollNo(String rollNo);
}
