package com.caiquepirs.api.DTOs;

import jakarta.validation.constraints.NotBlank;

public record CourseRequestDTO(
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Category is required")
        String category) {
}
