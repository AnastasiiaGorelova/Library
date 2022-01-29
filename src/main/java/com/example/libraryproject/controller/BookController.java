package com.example.libraryproject.controller;

import com.example.libraryproject.model.Book;
import com.example.libraryproject.model.User;
import com.example.libraryproject.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book-list")
    public String findAll(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "book-list";
    }

    @GetMapping("book-delete/{code_number}")
    public String deleteBook(@PathVariable("code_number") Long codeNumber) {
        bookService.deleteByCodeNumber(codeNumber);
        return "redirect:/book-list";
    }

    @GetMapping("/book-create")
    public String createBookForm(Book book) {
        return "book-create";
    }

    @PostMapping("/book-create")
    public String addBook(Book book) {
        bookService.saveBook(book);
        return "redirect:/book-list";
    }

    private Long hlpCodeNumber = (long) -1;

    @GetMapping("/book-update/{code_number}")
    public String updateUserForm(@PathVariable("code_number") Long codeNumber, Model model) {
        hlpCodeNumber = codeNumber;
        Book book = bookService.findByCodeNumber(codeNumber).get();
        model.addAttribute("book", book);
        return "book-update";
    }

    @PostMapping("/book-update")
    public String updateBook(Book book) {
        if (bookService.findByCodeNumber(book.getCodeNumber()).isEmpty()) {
            bookService.deleteByCodeNumber(hlpCodeNumber);
            bookService.saveBook(book);
        }
        else {
            //попытались изменить шифр книги на уже существующий
        }
        return "redirect:/book-list";
    }

    @GetMapping("/book-find")
    public String createFindBookForm(Book book) {
        return "book-find";
    }

    @PostMapping("/book-find")
    public String findBook(Book book) {
        hlpCodeNumber = book.getCodeNumber();
        return "book-find";
    }

    @GetMapping("/book-show")
    public String showBook(Model model) {
        Optional<Book> book = bookService.findByCodeNumber(hlpCodeNumber);
        model.addAttribute("book", book);
        return "book-show";
    }

    public List<Book> findByUserId(Long userId) {
        return bookService.findByUserId(userId);
    }

    @GetMapping("/user-takenbooks/{id}")
    public String findTakenBooks(@PathVariable("id") Long id, Model model) {
        List<Book> books = bookService.findByUserId(id);
        model.addAttribute("books", books);
        return "user-takenbooks";
    }

    @GetMapping("/book-givetouser/{code_number}")
    public String createGiveBookToUserForm(@PathVariable("code_number") Long codeNumber, User user) {
        hlpCodeNumber = codeNumber;
        return "book-givetouser";
    }

    @PostMapping("/book-givetouser")
    public String giveBookToUser(User user) {
        Book book = bookService.findByCodeNumber(hlpCodeNumber).get();
        book.setUserId(user.getId());
        bookService.saveBook(book);
        return "book-givetouser";
    }

    @GetMapping("/book-returntolibrary/{code_number}")
    public String returnBookToLibrary(@PathVariable("code_number") Long codeNumber) {
        Book book = bookService.findByCodeNumber(codeNumber).get();
        book.setUserId((long)-1);
        bookService.saveBook(book);
        return "redirect:/book-list";
    }
}