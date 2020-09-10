package com.example.demo.service.impl;

import com.example.demo.model.hessian.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.abstraction.BookService;
import com.example.demo.util.hessian.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;


    @Override
    @Transactional
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book byId(int id) throws NotFoundException {
        return bookRepository.getById(id).orElseThrow(() -> new NotFoundException("no.book"));
    }


    @Override
    @Transactional
    public Book byName(String name){
        return bookRepository.getByName(name);
    }


}
