package com.example.demo.repository;

import com.example.demo.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority,Integer> {

    Authority getById(int id);
}