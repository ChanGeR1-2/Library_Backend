package com.example.library_backend.Exceptions;

public class BookDoesNotExistException extends RuntimeException {
    public BookDoesNotExistException(String title, String author) {
        super("Book does not exist. Title: " + title + ", Author: " + author);
    }
}
