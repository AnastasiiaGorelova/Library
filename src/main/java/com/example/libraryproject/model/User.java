package com.example.libraryproject.model;

import lombok.Data;

import javax.persistence.*;

@Data  //getter,setter по умолчанию
@Entity  //сущность как-то связана с БД
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")  //иначе будет искать по другим именам
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

}
