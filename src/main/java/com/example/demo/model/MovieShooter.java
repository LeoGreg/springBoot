package com.example.demo.model;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class MovieShooter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany
    @JoinColumn(name = "movieShooter_id", referencedColumnName = "id")
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private List<Movie> movies;
}
