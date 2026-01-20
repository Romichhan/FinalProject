package com.sromey.project.service;

import com.sromey.project.dto.EnrollmentDTO;
import com.sromey.project.dto.StudentRequestDTO;
import com.sromey.project.entities.Course;
import com.sromey.project.entities.Enrollment;
import com.sromey.project.entities.Student;
import com.sromey.project.exception.CourseNotFoundException;
import com.sromey.project.exception.StudentNotFoundException;
import com.sromey.project.mapper.EnrollmentMapper;
import com.sromey.project.mapper.StudentMapper;
import com.sromey.project.repository.CourseRepository;
import com.sromey.project.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private EnrollmentMapper enrollmentMapper;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    @Transactional
    public void enrollStudent(StudentRequestDTO studentRequestDTO) {
        Student student = studentMapper.toEntity(studentRequestDTO);
        List<String> courseList = studentRequestDTO.getEnrollments().stream().map(
                EnrollmentDTO::getCourseCode
        ).toList();
        List<Course> fetchedCourses = courseRepository.findByCourseCodeIn(courseList);
        Map<String, Course> courseMap = fetchedCourses.stream()
                .collect(Collectors.toMap(Course::getCourseCode, Function.identity())); //Function.identity is basically course->course
        String missingCourses = courseList.stream().filter(course->!courseMap.containsKey(course))
                .collect(Collectors.joining(", "));
        if(!missingCourses.isBlank()){
            throw new CourseNotFoundException("The following courses are missing:"+missingCourses);
        }
        for(EnrollmentDTO enrollmentDTO : studentRequestDTO.getEnrollments()){
            String courseCode = enrollmentDTO.getCourseCode();
            Enrollment enrollment = enrollmentMapper.toEntity(enrollmentDTO);
            enrollment.addCourse(courseMap.get(courseCode));
            student.addEnrollment(enrollment);
        }
        studentRepository.save(student);
    }

    @Override
    public List<StudentRequestDTO> fetchAllStudents() {
        List<Student> fetchedStudents = studentRepository.fetchAllStudents();
        List<StudentRequestDTO> studentsList = new ArrayList<>();
        for(Student student: fetchedStudents){
            StudentRequestDTO studentDto = studentMapper.toDto(student);
            for(Enrollment enrollment : student.getEnrollments()){
                EnrollmentDTO enrollmentDTO = enrollmentMapper.toDto(enrollment);
                enrollmentDTO.setCourseCode(enrollment.getCourse().getCourseCode());
                studentDto.getEnrollments().add(enrollmentDTO);
            }
            studentsList.add(studentDto);
        }
        return studentsList;
    }

    @Override
    @Transactional
    public void patchStudent(String id, Object modifyValues) {
        Student student = studentRepository.findById(id).orElseThrow(()->new StudentNotFoundException("Student with id:"+id+" is not available"));
        if(modifyValues instanceof Map<?,?> patchValues){
            for(Map.Entry<?,?> entry : patchValues.entrySet()){
                String key = (String) entry.getKey();
                String value = (String) entry.getValue();
                if("rollNo".equals(key) && value!=null){
                    student.setRollNo(value);
                }
                else if("name".equals(key) && value!=null){
                    student.setName(value);
                }
            }
        }
    }

    @Override
    public StudentRequestDTO fetchByRollNo(String rollNo) {
        Student fetchedStudent = studentRepository.findByRollNo(rollNo).orElseThrow(()->new StudentNotFoundException("Student unavailable"));
        StudentRequestDTO studentRequestDTO = studentMapper.toDto(fetchedStudent);
        for(Enrollment enrollment : fetchedStudent.getEnrollments()){
            EnrollmentDTO enrollmentDTO = enrollmentMapper.toDto(enrollment);
            enrollmentDTO.setCourseCode(enrollment.getCourse().getCourseCode());
            studentRequestDTO.getEnrollments().add(enrollmentDTO);
        }
        return studentRequestDTO;
    }
}
