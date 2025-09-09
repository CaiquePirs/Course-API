package com.caiquepirs.api.controller;

import com.caiquepirs.api.dtos.CourseRequestDTO;
import com.caiquepirs.api.dtos.CourseResponseDTO;
import com.caiquepirs.api.mappers.CourseMapper;
import com.caiquepirs.api.model.Course;
import com.caiquepirs.api.service.CourseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
@AllArgsConstructor
public class CourseController {

    private final CourseService service;
    private final CourseMapper mapper;

    @PostMapping
    public ResponseEntity<CourseResponseDTO> create(@RequestBody @Valid CourseRequestDTO dto){
        Course course = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(course));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> getById(@PathVariable UUID id){
        Course course = service.findById(id);
        return ResponseEntity.ok(mapper.toDTO(course));
    }

    @GetMapping
    public ResponseEntity<Page<CourseResponseDTO>> getCourses(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        Page<Course> pageResult = service.findByQuery(name, category, page, size);
        Page<CourseResponseDTO> result = pageResult.map(mapper::toDTO);

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> update(@PathVariable UUID id, @RequestBody(required = false) CourseRequestDTO dto){
       Course course = service.update(id, dto);
       return ResponseEntity.ok(mapper.toDTO(course));
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<CourseResponseDTO> updateStatus(@PathVariable UUID id){
       Course course = service.toggleStatus(id);
       return ResponseEntity.ok(mapper.toDTO(course));
    }

}
