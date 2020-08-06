package com.example.demo.repository;

import com.example.demo.model.Movie;
import com.example.demo.model.MovieShooter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShooterRepository extends JpaRepository<MovieShooter, Integer> {

    MovieShooter save(MovieShooter movieShooter);
}
