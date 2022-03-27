package com.alkemy.challenge.backend.service;


import com.alkemy.challenge.backend.entity.Genero;
import com.alkemy.challenge.backend.entity.Pelicula;

import java.util.List;
import java.util.Set;

public interface IPeliculaService {

    List<Pelicula> getAll();

    List<Pelicula> findAllOrderByCreationDate(String order);

    Pelicula findById(Long movieId);

    List<Pelicula> findByTitulo(String titulo);

    List<Pelicula> findByTitle(String titulo);

    void delete(Long id);

    Pelicula save(Pelicula movie);

    List<Pelicula> findByGeneroId(Long idGenre);

    List<Genero> getGenero(Long id);

    void addGenero(Long movieId, List<Long> genresIds);

    void removeGenero(Long movieId, List<Long> genresIds);

    List<Pelicula> findByGenreId(Long idGenre);

    List<Genero> getGenres(Long id);

    void addGenres(Long movieId, List<Long> genresIds);

    void removeGenres(Long movieId, List<Long> genresIds);
}
