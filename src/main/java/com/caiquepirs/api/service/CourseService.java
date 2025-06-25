package com.caiquepirs.api.service;

import com.caiquepirs.api.DTOs.CourseRequestDTO;
import com.caiquepirs.api.exception.CourseNotFoundException;
import com.caiquepirs.api.exception.DuplicateCourseException;
import com.caiquepirs.api.mappers.CourseMapper;
import com.caiquepirs.api.model.CourseEntity;
import com.caiquepirs.api.model.StatusCourse;
import com.caiquepirs.api.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CourseService {

    private final CourseMapper mapper;
    private final CourseRepository repository;

    public CourseEntity create(CourseRequestDTO dto){
        repository.findByName(dto.name()).ifPresent(name -> {
            throw new DuplicateCourseException("There is already a course with this name");
        });
        var course = mapper.toEntity(dto);
        course.setStatus(StatusCourse.ACTIVE);
        return repository.save(course);
    }

    public CourseEntity getById(UUID id){
        return repository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course ID not found"));
    }

    public Page<CourseEntity> findByQuery(String name, String category, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name", "category").ascending());

        Specification<CourseEntity> spec = (root, query, cb) -> cb.conjunction();

        if (name != null && !name.isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
        }

        if (category != null && !category.isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(cb.lower(root.get("category")), category.toLowerCase()));
        }

        return repository.findAll(spec, pageable);
    }

    public void delete(UUID id){
       var course = getById(id);
       repository.delete(course);
    }

    public CourseEntity update(UUID id, CourseRequestDTO dto) {
        var existCourse = getById(id);

        if(dto.name() != null && !dto.name().isBlank()){
            existCourse.setName(dto.name());
        }

        if(dto.category() != null && !dto.category().isBlank()){
            existCourse.setCategory(dto.category());
        }

        repository.findByName(existCourse.getName()).ifPresent(course -> {
            if (!course.getId().equals(id)) {
                throw new DuplicateCourseException("There is already a course with this name");
            }
        });

        return repository.save(existCourse);
    }

    public CourseEntity toggleStatus(UUID id){
        var course = getById(id);

        if(course.getStatus() == StatusCourse.ACTIVE){
            course.setStatus(StatusCourse.INACTIVE);
        }else {
            course.setStatus(StatusCourse.ACTIVE);
        }

        return repository.save(course);
    }



}
