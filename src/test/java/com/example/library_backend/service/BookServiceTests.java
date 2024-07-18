package com.example.library_backend.service;

import com.example.library_backend.Exceptions.BookDoesNotExistException;
import com.example.library_backend.domain.Book;
import com.example.library_backend.persistence.BookRepository;
import com.example.library_backend.service.DTOs.BookDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.verify;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTests {
    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BookService bookService;

    @Test
    @DisplayName("Update method returns the correct DTO when existing book is found")
    public void correctUpdateMethodDtoValue() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("The Hobbit");
        book.setAuthor("J.R.R Tolkien");
        book.setPages(298);
        book.setRead(false);

        BookDTO bookDTO = new BookDTO("The Hobbit", "J.R.R Tolkien", 298, true);

        Book expectedBook = new Book();
        expectedBook.setId(1L);
        expectedBook.setTitle("The Hobbit");
        expectedBook.setAuthor("J.R.R Tolkien");
        expectedBook.setPages(298);
        expectedBook.setRead(true);

        given(bookRepository.findByTitleAndAuthor(bookDTO.title(), bookDTO.author()))
                .willReturn(Optional.of(book));
        given(bookRepository.save(book))
                .will((invocationOnMock) ->
                        invocationOnMock.getArgument(0).equals(expectedBook)
                                ? expectedBook
                                : null);

        BookDTO result = bookService.updateBook(bookDTO);

        verify(bookRepository)
                .findByTitleAndAuthor("The Hobbit", "J.R.R Tolkien");

        assertThat(result, is(equalTo(bookDTO)));
    }

    @Test
    @DisplayName("Update method throws exception if the book is not found")
    public void updateTargetNotFoundFlow() {
        BookDTO bookDTO = new BookDTO("The Hobbit", "J.R.R Tolkien", 298, true);

        given(bookRepository.findByTitleAndAuthor(bookDTO.title(), bookDTO.author()))
                .willReturn(Optional.empty());

        assertThrows(BookDoesNotExistException.class,
                () -> bookService.updateBook(bookDTO)
        );

        verify(bookRepository, never())
                .save(any());
    }
}
