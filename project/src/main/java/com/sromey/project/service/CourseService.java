package com.sromey.project.service;

import com.sromey.project.dto.CourseRequestDTO;
import com.sromey.project.dto.CourseResponseDTO;

import java.util.List;

public interface CourseService {
    void createCourse(CourseRequestDTO courseRequestDTO);
    List<CourseResponseDTO> getAllCourses();

}
