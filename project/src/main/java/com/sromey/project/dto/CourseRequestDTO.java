package com.sromey.project.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CourseRequestDTO {
    private String id;

    @NotBlank(message = "course code is mandatory")
    private String courseCode;

    @NotBlank(message = "course name is mandatory")
    private String courseName;
}
