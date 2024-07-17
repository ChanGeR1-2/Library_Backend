package com.example.library_backend.persistence;

import com.example.library_backend.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findDistinctByTitleAndAuthor(String title, String author);
}
