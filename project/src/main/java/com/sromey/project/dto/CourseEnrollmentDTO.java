package com.sromey.project.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CourseEnrollmentDTO {
    private String studentId;
    private String studentName;
    private LocalDate enrolledAt;
    private String enrollmentStatus;
}
