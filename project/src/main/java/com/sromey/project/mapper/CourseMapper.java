package com.sromey.project.mapper;

import com.sromey.project.dto.CourseRequestDTO;
import com.sromey.project.dto.CourseResponseDTO;
import com.sromey.project.entities.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(target="enrollments",ignore = true)
    CourseResponseDTO toDto(Course course);

    @Mapping(target = "enrollments",ignore = true)
    Course toEntity(CourseRequestDTO courseRequestDTO);
}
