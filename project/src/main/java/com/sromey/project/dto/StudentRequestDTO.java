package com.sromey.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StudentRequestDTO {
    private String id;

    @NotBlank(message = "Student name is mandatory")
    private String name;

    @NotBlank(message = "Student rollno is mandatory")
    private String rollNo;

    private AddressDTO address;

    @NotEmpty(message = "Enrollments are mandatory")
    private List<EnrollmentDTO> enrollments = new ArrayList<>();
}
