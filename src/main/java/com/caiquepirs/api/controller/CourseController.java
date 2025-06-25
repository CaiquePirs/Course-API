package com.caiquepirs.api.controller;

import com.caiquepirs.api.DTOs.CourseRequestDTO;
import com.caiquepirs.api.DTOs.CourseResponseDTO;
import com.caiquepirs.api.mappers.CourseMapper;
import com.caiquepirs.api.service.CourseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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








}
