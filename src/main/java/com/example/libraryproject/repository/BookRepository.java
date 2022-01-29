//для взаимодействия с БД
package com.example.libraryproject.repository;

import com.example.libraryproject.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByUserId(Long userId);
}
