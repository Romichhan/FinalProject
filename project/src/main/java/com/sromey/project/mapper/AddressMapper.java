package com.sromey.project.mapper;

import com.sromey.project.dto.AddressDTO;
import com.sromey.project.entities.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressDTO toDto(Address address);
    Address toEntity(AddressDTO addressDTO);
}
