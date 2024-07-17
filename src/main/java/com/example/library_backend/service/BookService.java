package com.example.library_backend.service;

import com.example.library_backend.service.DTOs.BookDTO;
import com.example.library_backend.domain.Book;
import com.example.library_backend.persistence.BookRepository;
import com.example.library_backend.service.DTOs.BookMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@AllArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;

    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(BookMapper.INSTANCE::bookToBookDTO).toList();
    }

    public void addBook(BookDTO bookDTO) {
        bookRepository.save(BookMapper.INSTANCE.bookDTOToBook(bookDTO));
    }

    public boolean updateBook(BookDTO bookDTO) {
        Optional<Book> opt = bookRepository.findByTitleAndAuthor(bookDTO.title(), bookDTO.author());
        if (opt.isPresent()) {
            Book book = opt.get();
            book.setTitle(bookDTO.title());
            book.setAuthor(bookDTO.author());
            book.setRead(bookDTO.read());
            book.setPages(bookDTO.pages());
            bookRepository.save(book);

            return true;
        }
        return false;
    }
}
