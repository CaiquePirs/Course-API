package com.caiquepirs.api.mappers;

import com.caiquepirs.api.DTOs.CourseRequestDTO;
import com.caiquepirs.api.DTOs.CourseResponseDTO;
import com.caiquepirs.api.model.CourseEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseEntity toEntity(CourseRequestDTO dto);
    CourseResponseDTO toDTO(CourseEntity entity);
}
