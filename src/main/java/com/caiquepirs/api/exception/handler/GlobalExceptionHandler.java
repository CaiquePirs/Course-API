package com.caiquepirs.api.exception.handler;

import com.caiquepirs.api.exception.CourseNotFoundException;
import com.caiquepirs.api.exception.DTO.ErrorFieldDTO;
import com.caiquepirs.api.exception.DTO.ErrorResponseDTO;
import com.caiquepirs.api.exception.DuplicateCourseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleCourseNotFound(CourseNotFoundException e) {
        ErrorFieldDTO error = new ErrorFieldDTO("Not found", e.getMessage());
        ErrorResponseDTO responseDTO = new ErrorResponseDTO(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                List.of(error));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    @ExceptionHandler(DuplicateCourseException.class)
    public ResponseEntity<ErrorResponseDTO> handleDuplicateCourse(DuplicateCourseException e) {
        ErrorFieldDTO error = new ErrorFieldDTO("Conflict", e.getMessage());
        ErrorResponseDTO responseDTO = new ErrorResponseDTO(
                HttpStatus.CONFLICT.value(),
                e.getMessage(),
                List.of(error));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseDTO);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        ErrorFieldDTO error = new ErrorFieldDTO("Method", "Method Not Allowed");
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                HttpStatus.METHOD_NOT_ALLOWED.value(),
                "Method not allowed for this endpoint.",
                List.of(error));
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errorResponse);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> handleInternalError(RuntimeException e){
        ErrorFieldDTO error = new ErrorFieldDTO("Error", e.getMessage());
        ErrorResponseDTO responseDTO = new ErrorResponseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An unexpected error occurred",
                List.of(error));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleBadRequest(MethodArgumentNotValidException e){
        List<ErrorFieldDTO> listErrors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fe -> new ErrorFieldDTO(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());

        ErrorResponseDTO responseDTO = new ErrorResponseDTO(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Validation error",
                listErrors);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(responseDTO);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    ResponseEntity<ErrorResponseDTO> handleNotFound(NoHandlerFoundException e){
        ErrorFieldDTO error = new ErrorFieldDTO("URL", e.getMessage());
        ErrorResponseDTO responseDTO = new ErrorResponseDTO(
                HttpStatus.NOT_FOUND.value(),
                "Url not found",
                List.of(error));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    ResponseEntity<ErrorResponseDTO> handleNoResourceFound(NoResourceFoundException e){
        ErrorFieldDTO error = new ErrorFieldDTO("URL", "Url not found");
        ErrorResponseDTO responseDTO = new ErrorResponseDTO(
                HttpStatus.NOT_FOUND.value(),
                "Url not found",
                List.of(error));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

}
