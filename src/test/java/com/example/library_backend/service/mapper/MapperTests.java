package com.example.library_backend.service.mapper;

import com.example.library_backend.domain.Book;
import com.example.library_backend.service.DTOs.BookDTO;
import com.example.library_backend.service.DTOs.BookMapper;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MapperTests {

    @Test
    public void shouldMapBookToBookDto() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Book Title");
        book.setAuthor("Author");
        book.setRead(true);
        book.setPages(298);

        BookDTO bookDto = BookMapper.INSTANCE.bookToBookDTO(book);

        assertThat(bookDto, is(notNullValue()));
        assertThat(bookDto.author(), equalTo(book.getAuthor()));
        assertThat(bookDto.title(), equalTo(book.getTitle()));
        assertThat(bookDto.read(), equalTo(book.isRead()));
        assertThat(bookDto.pages(), equalTo(book.getPages()));
    }
}
