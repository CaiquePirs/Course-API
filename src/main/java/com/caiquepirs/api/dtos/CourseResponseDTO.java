package com.caiquepirs.api.dtos;

import com.caiquepirs.api.model.StatusCourse;

import java.time.LocalDateTime;
import java.util.UUID;

public record CourseResponseDTO(UUID id,
                                String name,
                                String category,
                                StatusCourse status,
                                LocalDateTime created_at,
                                LocalDateTime updated_at) {
}
