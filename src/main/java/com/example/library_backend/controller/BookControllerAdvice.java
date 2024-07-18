package com.example.library_backend.controller;

import com.example.library_backend.Exceptions.BookDoesNotExistException;
import com.example.library_backend.Exceptions.BookExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BookControllerAdvice {

    @ExceptionHandler({BookDoesNotExistException.class, BookExistsException.class})
    public ResponseEntity<ErrorDetails> handleBookExceptions(RuntimeException e) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorDetails(e.getMessage()));
    }
}
