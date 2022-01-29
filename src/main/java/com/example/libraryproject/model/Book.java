package com.example.libraryproject.model;

import lombok.Data;

import javax.persistence.*;

@Data  //getter,setter по умолчанию
@Entity  //сущность как-то связана с БД
@Table(name = "books")
public class Book {
    @Id
    @Column(name = "code_number")
    private Long codeNumber;
    private String name;
    private String author;
    @Column(name = "user_id")
    private Long userId;

}
