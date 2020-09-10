package com.example.demo.repository;

import com.example.demo.model.hessian.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {


    Optional<Book> getById(int id);

    Book save(Book book);

    Book getByName(String name);

}
