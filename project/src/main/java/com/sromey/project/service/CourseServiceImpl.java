package com.sromey.project.service;


import com.sromey.project.dto.CourseEnrollmentDTO;
import com.sromey.project.dto.CourseRequestDTO;
import com.sromey.project.dto.CourseResponseDTO;
import com.sromey.project.entities.Course;
import com.sromey.project.entities.Enrollment;
import com.sromey.project.mapper.CourseMapper;
import com.sromey.project.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void createCourse(CourseRequestDTO courseRequestDTO) {
        System.out.println("Inside create course");
        Course course = courseMapper.toEntity(courseRequestDTO);
        courseRepository.save(course);
    }

    @Override
    public List<CourseResponseDTO> getAllCourses() {
        System.out.println("Inside get all courses");
        List<Course> fetchedCourses = courseRepository.findCourseWithEnrollments();
        List<CourseResponseDTO> courseResponseList = new ArrayList<>();
        for(Course course : fetchedCourses){
            CourseResponseDTO courseResponse = courseMapper.toDto(course);
            for(Enrollment enrollment : course.getEnrollments()){
                CourseEnrollmentDTO courseEnrollmentDTO = new CourseEnrollmentDTO();
                courseEnrollmentDTO.setEnrolledAt(enrollment.getEnrolledAt());
                courseEnrollmentDTO.setStudentId(enrollment.getStudent().getId());
                courseEnrollmentDTO.setStudentName(enrollment.getStudent().getName());
                courseEnrollmentDTO.setEnrollmentStatus(enrollment.getStatus().name());
                courseResponse.getEnrollments().add(courseEnrollmentDTO);
            }
            courseResponseList.add(courseResponse);
        }
        return courseResponseList;
    }
}
