package com.sromey.project.controller;

import com.sromey.project.dto.ApiResponse;
import com.sromey.project.dto.CourseRequestDTO;
import com.sromey.project.dto.CourseResponseDTO;
import com.sromey.project.dto.StudentRequestDTO;
import com.sromey.project.service.CourseService;
import com.sromey.project.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProjectController {

    private final CourseService courseService;
    private final StudentService studentService;

    @PostMapping("/enroll")
    public ResponseEntity<ApiResponse<String>> enrollStudent(
            @RequestBody @Valid StudentRequestDTO studentRequestDTO
            ) {
        studentService.enrollStudent(studentRequestDTO);
        ApiResponse<String> apiResponse = new ApiResponse<>(null, "Student successfully enrolled", null);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping("/students")
    public ResponseEntity<ApiResponse<List<StudentRequestDTO>>> fetchAllStudents(){
        List<StudentRequestDTO> students = studentService.fetchAllStudents();
        return new ResponseEntity<>(new ApiResponse<>(students,"Successfully fetched all students",null),HttpStatus.OK);
    }

    @GetMapping("/students/{rollNo}")
    public ResponseEntity<ApiResponse<StudentRequestDTO>> fetchByRollNo(
            @PathVariable("rollNo") String rollNo
    ){
        StudentRequestDTO student = studentService.fetchByRollNo(rollNo);
        return new ResponseEntity<>(new ApiResponse<>(student,"Successfully fetched all students",null),HttpStatus.OK);
    }

    @GetMapping("/courses")
    public ResponseEntity<ApiResponse<List<CourseResponseDTO>>> getAllCourses(){
        List<CourseResponseDTO> courses = courseService.getAllCourses();
        ApiResponse<List<CourseResponseDTO>> apiResponse = new ApiResponse<>(courses,"Successfully fetched all courses",null);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @PostMapping("/course")
    public ResponseEntity<ApiResponse<String>> addCourse(
            @RequestBody @Valid CourseRequestDTO courseRequestDTO
            ){
        courseService.createCourse(courseRequestDTO);
        ApiResponse<String> apiResponse = new ApiResponse<>(null,"Course successfully added",null);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @PatchMapping("/modifystudent")
    public ResponseEntity<ApiResponse<String>> patchStudent(
            @RequestParam(required = true,name="student") String id,
            @RequestBody Object modifyValues
    ){
        studentService.patchStudent(id,modifyValues);
        return new ResponseEntity<>(new ApiResponse<>(null,"Successfully patched student",null),HttpStatus.OK);
    }
}
