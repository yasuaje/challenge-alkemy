package com.alkemy.challenge.backend.repository;

import com.alkemy.challenge.backend.entity.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GeneroRepository extends JpaRepository<Genero, Long> {

    public Optional<Genero> findById(Long id);
    public Genero getById(Long Id);
}