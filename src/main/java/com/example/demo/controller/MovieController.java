package com.example.demo.controller;

import com.example.demo.model.Movie;
import com.example.demo.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;


    @PostMapping("/add")
    public ResponseEntity addMovie(@Valid @RequestBody Movie movie) {
        return ResponseEntity.ok(movieRepository.save(movie));
    }


    @GetMapping("/findByShooter/{movieShooterName}")
    public ResponseEntity findByShooterName(@PathVariable String movieShooterName){
        return ResponseEntity.ok(movieRepository.findAllByMovieShooterName(movieShooterName));
    }

    @GetMapping("/findByBook/{name}")
    public ResponseEntity findByName(@PathVariable String name){
        return ResponseEntity.ok(movieRepository.findAllByName(name));
    }

}
