package com.alkemy.challenge.backend.service;

import com.alkemy.challenge.backend.entity.Personaje;

import java.util.List;

public interface IPersonajeService {

    List<Personaje> getAll();

    Personaje findById(Long characterId);

    List<Personaje> findByName(String name);

    List<Personaje> findByAge(Integer age);

    void delete(Long id);

    Character save(Character character);

    Personaje save(Personaje personaje);

    List<Personaje> findByMovieId(Long idMovie);

    void addMovies(Long characterId, List<Long> moviesIds);

    void removeMovies(Long characterId, List<Long> moviesIds);

}
