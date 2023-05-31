package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    @Query("Select u from User u Where u.username= ?1")
    User findByUsername(String username);
}
