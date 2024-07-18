package com.example.library_backend.service;

import com.example.library_backend.Exceptions.BookDoesNotExistException;
import com.example.library_backend.Exceptions.BookExistsException;
import com.example.library_backend.service.DTOs.BookDTO;
import com.example.library_backend.domain.Book;
import com.example.library_backend.persistence.BookRepository;
import com.example.library_backend.service.DTOs.BookMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@AllArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;

    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(BookMapper.INSTANCE::bookToBookDTO).toList();
    }

    public BookDTO addBook(BookDTO bookDTO) {
        bookRepository.findByTitleAndAuthor(bookDTO.title(), bookDTO.author())
                .ifPresent(_ -> {
                    throw new BookExistsException(bookDTO.title(), bookDTO.author());
                });

        return BookMapper.INSTANCE.bookToBookDTO(bookRepository.save(BookMapper.INSTANCE.bookDTOToBook(bookDTO)));
    }

    public BookDTO updateBook(BookDTO bookDTO) {
        Book book = bookRepository.findByTitleAndAuthor(bookDTO.title(), bookDTO.author())
                .orElseThrow(() -> new BookDoesNotExistException(bookDTO.title(), bookDTO.author()));

        book.setTitle(bookDTO.title());
        book.setAuthor(bookDTO.author());
        book.setRead(bookDTO.read());
        book.setPages(bookDTO.pages());
        return BookMapper.INSTANCE.bookToBookDTO(bookRepository.save(book));
    }

    public void deleteBook(BookDTO bookDTO) {
        Book book = bookRepository.findByTitleAndAuthor(bookDTO.title(), bookDTO.author())
                .orElseThrow(() -> new BookDoesNotExistException(bookDTO.title(), bookDTO.author()));

        bookRepository.deleteById(book.getId());
    }
}
