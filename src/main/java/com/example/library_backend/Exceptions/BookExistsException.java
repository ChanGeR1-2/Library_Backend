package com.example.library_backend.Exceptions;

public class BookExistsException extends RuntimeException {
    public BookExistsException(String title, String author) {
        super("Book already exists with title " + title + " and author " + author);
    }
}
