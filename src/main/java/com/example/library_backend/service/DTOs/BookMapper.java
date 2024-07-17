package com.example.library_backend.service.DTOs;

import com.example.library_backend.domain.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);
    BookDTO bookToBookDTO(Book book);
    Book bookDTOToBook(BookDTO bookDTO);
}
