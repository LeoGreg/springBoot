package com.example.demo.service.abstraction;

import com.example.demo.model.hessian.Book;
import com.example.demo.util.hessian.NotFoundException;

import javax.transaction.Transactional;

public interface BookService {
    @Transactional
    Book save(Book book);

    @Transactional
    Book byId(int id) throws NotFoundException;

    @Transactional
    Book byName(String name);
}
