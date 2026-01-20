package com.sromey.project.mapper;

import com.sromey.project.dto.EnrollmentDTO;
import com.sromey.project.entities.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {

    @Mapping(target="courseCode",ignore = true)
    EnrollmentDTO toDto(Enrollment enrollment);

   @Mapping(target="course",ignore = true)
   @Mapping(target="student",ignore=true)
   Enrollment toEntity(EnrollmentDTO enrollmentDTO);
}
