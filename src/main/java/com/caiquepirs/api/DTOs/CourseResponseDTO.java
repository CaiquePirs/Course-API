package com.caiquepirs.api.DTOs;

import java.time.LocalDateTime;
import java.util.UUID;

public record CourseResponseDTO(UUID id,
                                String name,
                                String category,
                                LocalDateTime created_at,
                                LocalDateTime updated_at) {
}
