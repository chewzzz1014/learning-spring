package com.example.readinglist.book;

import com.example.readinglist.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByReader(String reader);
}
