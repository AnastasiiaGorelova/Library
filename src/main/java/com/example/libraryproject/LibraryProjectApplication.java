package com.example.libraryproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryProjectApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(LibraryProjectApplication.class, args);
        } catch (Exception e) {
            System.err.println("Application ending due to " + e.getMessage());
        }
    }

}
