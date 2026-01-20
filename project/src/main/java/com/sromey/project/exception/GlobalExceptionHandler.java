package com.sromey.project.exception;

import com.sromey.project.controller.ProjectController;
import com.sromey.project.dto.ApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice(assignableTypes = ProjectController.class) //catches errors in ProjectController
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationErrors(MethodArgumentNotValidException ex){
        String error = ex.getBindingResult().getFieldErrors().stream().map(
                err -> err.getField()+":"+err.getDefaultMessage()
        ).collect(Collectors.joining(", "));
        ApiResponse<Object> apiResponse = new ApiResponse<>(null,null,error);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<Object>> handleDataIntegrity(DataIntegrityViolationException ex){
        ApiResponse<Object> apiResponse = new ApiResponse<>(null,ex.getMessage(),null);
        return new ResponseEntity<>(apiResponse,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleMissingCourse(CourseNotFoundException ex){
        ApiResponse<Object> apiResponse = new ApiResponse<>(null,ex.getMessage(),null);
        return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleMissingStudent(StudentNotFoundException ex){
        ApiResponse<Object> apiResponse = new ApiResponse<>(null,ex.getMessage(),null);
        return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
    }


}
