package com.alkemy.challenge.backend.repository;

import com.alkemy.challenge.backend.entity.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonajeRepository extends JpaRepository<Personaje, Long> {
    Optional<Personaje> findById(Long id);

    List<Personaje> findByNombre(String nombre);

    List<Personaje> findByEdad(Integer edad);

    List<Personaje> findByMoviesId(Long id);
}