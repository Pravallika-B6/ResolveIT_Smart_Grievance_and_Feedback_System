package com.example.demo.Model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MyAppUserRepositry extends JpaRepository<MyAppUser, Long>{

    Optional<MyAppUser> findByEmail(String email);

} 