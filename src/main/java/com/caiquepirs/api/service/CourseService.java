package com.caiquepirs.api.service;

import com.caiquepirs.api.dtos.CourseRequestDTO;
import com.caiquepirs.api.exception.CourseNotFoundException;
import com.caiquepirs.api.exception.DuplicateCourseException;
import com.caiquepirs.api.mappers.CourseMapper;
import com.caiquepirs.api.model.Course;
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

    public Course create(CourseRequestDTO dto){
        repository.findByName(dto.name()).ifPresent(name -> {
            throw new DuplicateCourseException("There is already a course with this name");
        });
        Course course = mapper.toEntity(dto);
        course.setStatus(StatusCourse.ACTIVE);
        return repository.save(course);
    }

    public Course findById(UUID id){
        return repository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course ID not found"));
    }

    public Page<Course> findByQuery(String name, String category, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name", "category").ascending());

        Specification<Course> spec = (root, query, cb) -> cb.conjunction();

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
       Course course = findById(id);
       repository.delete(course);
    }

    public Course update(UUID id, CourseRequestDTO dto) {
        Course course = findById(id);

        if(dto.name() != null && !dto.name().isBlank()) {
            repository.findByName(dto.name()).ifPresent(exist -> {
                if (!course.getId().equals(id)) {
                    throw new DuplicateCourseException("There is already a course with this name");
                }
            });
            course.setName(dto.name());
        }
        if(dto.category() != null && !dto.category().isBlank()) course.setCategory(dto.category());
        return repository.save(course);
    }

    public Course toggleStatus(UUID id){
        Course course = findById(id);

        if (course.getStatus() == StatusCourse.ACTIVE) course.setStatus(StatusCourse.INACTIVE);
        else course.setStatus(StatusCourse.ACTIVE);
        return repository.save(course);
    }



}
