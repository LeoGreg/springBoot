package com.example.demo.repository;

import com.example.demo.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {

    Movie save(Movie movie);

    List<Movie> findAllByMovieShooterName(String name);

    List<Movie> findAllByName(String name);
}
