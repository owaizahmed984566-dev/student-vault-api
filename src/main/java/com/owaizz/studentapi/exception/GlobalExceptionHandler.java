package com.owaizz.studentapi.exception;

import com.owaizz.studentapi.dto.MessageResponse;

import org.springframework.http.ResponseEntity;

import org.springframework.security.authorization.AuthorizationDeniedException;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageResponse> handleGeneralException(
            Exception e) {

        return ResponseEntity
                .status(500)
                .body(new MessageResponse("Something went wrong"));
    }


    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<MessageResponse> handleStudentNotFound(
            StudentNotFoundException e) {

        return ResponseEntity
                .status(404)
                .body(new MessageResponse(e.getMessage()));
    }


    @ExceptionHandler(InvalidStudentException.class)
    public ResponseEntity<MessageResponse> handleInvalidStudent(
            InvalidStudentException e) {

        return ResponseEntity
                .status(400)
                .body(new MessageResponse(e.getMessage()));
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<MessageResponse> handleTypeMismatch(
            MethodArgumentTypeMismatchException e) {

        return ResponseEntity
                .status(400)
                .body(new MessageResponse("ID must be an integer"));
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageResponse> handleValidationException(
            MethodArgumentNotValidException e) {

        String message = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error ->
                        error.getField() + ": "
                                + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return ResponseEntity
                .status(400)
                .body(new MessageResponse(message));
    }


    // USER is authenticated but does not have
    // the required ADMIN role
    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<MessageResponse> handleAuthorizationDenied(
            AuthorizationDeniedException e) {

        return ResponseEntity
                .status(403)
                .body(new MessageResponse("Access denied"));
    }
}