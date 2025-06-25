package com.caiquepirs.api.service;

import com.caiquepirs.api.DTOs.CourseRequestDTO;
import com.caiquepirs.api.exception.CourseNotFoundException;
import com.caiquepirs.api.exception.DuplicateCourseException;
import com.caiquepirs.api.mappers.CourseMapper;
import com.caiquepirs.api.model.CourseEntity;
import com.caiquepirs.api.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CourseService {

    private final CourseMapper mapper;
    private final CourseRepository repository;

    public CourseEntity create(CourseRequestDTO dto){
        var existCourseByName = repository.findByName(dto.name()).isPresent();

        if(existCourseByName){
            throw new DuplicateCourseException("There is already a course with this name");
        }

        return repository.save(mapper.toEntity(dto));
    }

    public CourseEntity getById(UUID id){
        return repository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course ID not found"));
    }
}
