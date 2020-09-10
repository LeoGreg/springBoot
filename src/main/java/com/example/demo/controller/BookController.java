package com.example.demo.controller;


import com.example.demo.model.hessian.Book;
import com.example.demo.service.abstraction.BookService;
import com.example.demo.util.hessian.NotFoundException;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Data
@Log4j2
@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/id")
    public ResponseEntity by_id(@RequestParam int id) throws NotFoundException {
        return ResponseEntity.ok(bookService.byId(id));
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.save(book));
    }

    @GetMapping("/name")
    public ResponseEntity by_name(@RequestParam String name) {
        return ResponseEntity.ok(bookService.byName(name));
    }
}
