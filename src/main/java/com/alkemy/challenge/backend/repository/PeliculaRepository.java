package com.alkemy.challenge.backend.repository;

import com.alkemy.challenge.backend.entity.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
    Optional<Pelicula> findById(Long id);

    List<Pelicula> findByTitulo(String titulo);

    List<Pelicula> findByGenresId(Long genreId);

    List<Pelicula> findAllByOrderByCreationDateAsc();

    @Query(value = "SELECT title,image,creation_date FROM movies",nativeQuery = true)
    public Iterable<Object[]> getAll();

    List<Pelicula> findAllByOrderByCreationDateDesc();
    @Query(value = "SELECT title,image,creation_date FROM movies ORDER BY creation_date ASC",nativeQuery = true)
    public Iterable<Object[]> getAllByOrderASC();

    @Query(value = "SELECT title,image,creation_date FROM movies ORDER BY creation_date DESC",nativeQuery = true)
    public Iterable<Object[]> getAllByOrderDESC();
}