package com.example.db_course.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.db_course.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email);
}
