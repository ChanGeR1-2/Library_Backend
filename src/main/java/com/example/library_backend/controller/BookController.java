package com.example.library_backend.controller;

import com.example.library_backend.service.DTOs.BookDTO;
import com.example.library_backend.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Validated
@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> bookDTOList = bookService.getAllBooks();
        return ResponseEntity.ok(bookDTOList);
    }

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody BookDTO bookDTO) {
        BookDTO result = bookService.addBook(bookDTO);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping
    public ResponseEntity<?> updateBook(@RequestBody BookDTO bookDTO) {
        BookDTO bookDto = bookService.updateBook(bookDTO);

        return ResponseEntity.ok().body(bookDto);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteBook(@RequestBody BookDTO bookDTO) {
        bookService.deleteBook(bookDTO);

        return ResponseEntity.ok().build();
    }
}
