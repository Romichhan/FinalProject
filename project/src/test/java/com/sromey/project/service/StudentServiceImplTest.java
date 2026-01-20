package com.sromey.project.service;

import com.sromey.project.dto.EnrollmentDTO;
import com.sromey.project.dto.StudentRequestDTO;
import com.sromey.project.entities.Course;
import com.sromey.project.entities.Enrollment;
import com.sromey.project.entities.Student;
import com.sromey.project.exception.StudentNotFoundException;
import com.sromey.project.mapper.EnrollmentMapper;
import com.sromey.project.mapper.StudentMapper;
import com.sromey.project.repository.CourseRepository;
import com.sromey.project.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceImplTest {

    @Mock
    private StudentMapper studentMapper;

    @Mock
    private EnrollmentMapper enrollmentMapper;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentServiceImpl;

    @Test
    public void fetchByRollNoTest(){
        System.out.println("Inside fetch by rollno test");
        Student student = new Student();
        student.setRollNo("23");

        Course course1 = new Course();
        course1.setCourseCode("C01");
        Course course2 = new Course();
        course2.setCourseCode("C02");

        Enrollment enrollment1 = new Enrollment();
        enrollment1.addCourse(course1);
        Enrollment enrollment2 = new Enrollment();
        enrollment2.addCourse(course2);

        student.addEnrollment(enrollment1);
        student.addEnrollment(enrollment2);

        StudentRequestDTO requestDTO = new StudentRequestDTO();
        when(studentRepository.findByRollNo("23")).thenReturn(Optional.of(student));
        when(studentMapper.toDto(student)).thenReturn(requestDTO);
        when(enrollmentMapper.toDto(any(Enrollment.class))).thenAnswer(inv ->new EnrollmentDTO());
        StudentRequestDTO result = studentServiceImpl.fetchByRollNo("23");
        assertNotNull(result, "Result should not be null");
        assertEquals(2,result.getEnrollments().size(),"Should have 2 enrollments");
        assertEquals("C01", result.getEnrollments().get(0).getCourseCode());
        assertEquals("C02", result.getEnrollments().get(1).getCourseCode());

    }

    @Test
    public void negativeFetchRollNoTest(){
        when(studentRepository.findByRollNo("R1")).thenReturn(Optional.empty());
        assertThrows(StudentNotFoundException.class,()->studentServiceImpl.fetchByRollNo("R1"),"Should throw student not found exception");
    }
}
