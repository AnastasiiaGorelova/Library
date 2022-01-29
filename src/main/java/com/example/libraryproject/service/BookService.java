//обертка над repository, принимает запросы из внешнего мира
package com.example.libraryproject.service;

import com.example.libraryproject.model.Book;
import com.example.libraryproject.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Optional<Book> findByCodeNumber(Long codeNumber) {
        return bookRepository.findById(codeNumber);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<Book> findByUserId(Long userId) {
        return bookRepository.findByUserId(userId);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteByCodeNumber(Long codeNumber) {
        bookRepository.deleteById(codeNumber);
    }
}