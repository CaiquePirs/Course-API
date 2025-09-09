package com.caiquepirs.api.dtos;

import jakarta.validation.constraints.NotBlank;

public record CourseRequestDTO(
        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Category is required")
        String category) {
}
