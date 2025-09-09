package com.caiquepirs.api.exception.dtos;

import java.util.List;

public record ErrorResponseDTO(int Status, String message, List<ErrorFieldDTO> error) {
}
