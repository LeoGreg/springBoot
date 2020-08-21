package com.example.demo.controller;

import com.example.demo.model.Movie;
import com.example.demo.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/interconnect")
public class BackController {

    @Autowired
    private MovieRepository movieRepository;

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody Movie movie) {
        movieRepository.save(movie);
        return ResponseEntity.ok(movie);
    }
    @GetMapping("/getMovies")
    public ResponseEntity getAll(){
        return ResponseEntity.ok(movieRepository.findAll());
    }
}
