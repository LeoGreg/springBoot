package com.example.demo.repository;


import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Pattern;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM user WHERE username=:Username")
    User getByUsername(@Param("Username") String username);

    User save(User user);


}
