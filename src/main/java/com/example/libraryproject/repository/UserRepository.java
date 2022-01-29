//для взаимодействия с БД
package com.example.libraryproject.repository;

import com.example.libraryproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
