package com.caiquepirs.api.controller;

import com.caiquepirs.api.DTOs.CourseRequestDTO;
import com.caiquepirs.api.DTOs.CourseResponseDTO;
import com.caiquepirs.api.mappers.CourseMapper;
import com.caiquepirs.api.model.CourseEntity;
import com.caiquepirs.api.service.CourseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/courses")
@AllArgsConstructor
public class CourseController {

    private final CourseService service;
    private final CourseMapper mapper;

    @PostMapping
    public ResponseEntity<CourseResponseDTO> create(@RequestBody @Valid CourseRequestDTO dto){
        var course = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(course));
    }

    @GetMapping("{id}")
    public ResponseEntity<CourseResponseDTO> getById(@PathVariable UUID id){
        var course = service.getById(id);
        return ResponseEntity.ok(mapper.toDTO(course));
    }

    @GetMapping
    public ResponseEntity<Page<CourseResponseDTO>> getCourses(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        Page<CourseEntity> pageResult = service.findByQuery(name, category, page, size);
        Page<CourseResponseDTO> result = pageResult.map(mapper::toDTO);

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }








}
