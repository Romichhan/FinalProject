package com.sromey.project.mapper;

import com.sromey.project.dto.StudentRequestDTO;
import com.sromey.project.entities.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface StudentMapper {

    @Mapping(target="enrollments",ignore=true)
    Student toEntity(StudentRequestDTO studentRequestDTO);


    @Mapping(target="enrollments",ignore=true)
    StudentRequestDTO toDto(Student student);
}
