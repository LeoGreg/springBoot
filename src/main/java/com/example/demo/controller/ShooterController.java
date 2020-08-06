package com.example.demo.controller;

import com.example.demo.model.Movie;
import com.example.demo.model.MovieShooter;
import com.example.demo.repository.ShooterRepository;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import java.util.List;

@RestController
@RequestMapping("/shooter")
public class ShooterController {

    @Autowired
    private ShooterRepository shooterRepository;

    @Autowired
    private EntityManager entityManager;
/*

    @Override
    public List<Book> searchByName(String name) throws RuntimeException {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> card = cq.from(Book.class);
        Predicate carNumberPredict = cb.like(card.get("name"), "%" + name + "%");
        cq.where(carNumberPredict);
        TypedQuery<Book> query = em.createQuery(cq);
        return query.getResultList();
    }
 */

    @PostMapping("/add")
    public ResponseEntity addShooter(@Valid @RequestBody MovieShooter movieShooter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Movie> criteriaQuery = criteriaBuilder.createQuery(Movie.class);
        Root<Movie> movieRoot = criteriaQuery.from(Movie.class);
        Predicate predicate = criteriaBuilder.like(movieRoot.get("movieShooterName"), "%" + movieShooter.getName() + "%");
        criteriaQuery.where(predicate);
        TypedQuery<Movie> query = entityManager.createQuery(criteriaQuery);
        movieShooter.setMovies(query.getResultList());
        return ResponseEntity.ok(shooterRepository.save(movieShooter));
    }

    @GetMapping("/getAll")
    public ResponseEntity findAll() {
        return ResponseEntity.ok(shooterRepository.findAll());
    }

}
