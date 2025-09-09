package com.caiquepirs.api.mappers;

import com.caiquepirs.api.dtos.CourseRequestDTO;
import com.caiquepirs.api.dtos.CourseResponseDTO;
import com.caiquepirs.api.model.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    Course toEntity(CourseRequestDTO dto);
    CourseResponseDTO toDTO(Course entity);
}
