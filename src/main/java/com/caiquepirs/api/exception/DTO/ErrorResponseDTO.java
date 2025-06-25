package com.caiquepirs.api.exception.DTO;

import org.springframework.http.ResponseEntity;

import java.util.List;

public record ErrorResponseDTO(int Status, String message, List<ErrorFieldDTO> error) {
}
