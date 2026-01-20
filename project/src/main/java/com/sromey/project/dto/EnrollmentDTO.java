package com.sromey.project.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EnrollmentDTO {
    private String id;
    private String courseCode;
    private LocalDate enrolledAt;
    private String status;
}