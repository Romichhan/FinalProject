package com.sromey.project.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CourseResponseDTO {
    private String id;
    private String courseCode;
    private String courseName;
    private List<CourseEnrollmentDTO> enrollments = new ArrayList<>();
}
