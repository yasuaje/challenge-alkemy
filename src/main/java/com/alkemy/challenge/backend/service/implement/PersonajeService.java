package com.alkemy.challenge.backend.service.implement;

import com.alkemy.challenge.backend.entity.Pelicula;
import com.alkemy.challenge.backend.entity.Personaje;
import com.alkemy.challenge.backend.exception.ResourceNotFoundException;
import com.alkemy.challenge.backend.repository.PeliculaRepository;
import com.alkemy.challenge.backend.repository.PersonajeRepository;
import com.alkemy.challenge.backend.service.IPersonajeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public abstract class PersonajeService implements IPersonajeService {

    private final PersonajeRepository personajeRepository;
    private final PeliculaRepository peliculaRepository;

    @Override
    public List<Personaje> getAll() {

        return personajeRepository.findAll();

    }

    @Override
    public Personaje findById(Long characterId) {

        return personajeRepository.findById(characterId).orElseThrow(() -> new ResourceNotFoundException("No Character with ID : " + characterId));

    }

    @Override
    public List<Personaje> findByName(String nombre) {

        return personajeRepository.findByNombre(nombre);

    }

    @Override
    public List<Personaje> findByAge(Integer edad) {

        return personajeRepository.findByEdad(edad);

    }

    @Override
    public void delete(Long id) {

        personajeRepository.delete(findById(id));

    }

    @Override
    public Personaje save(Personaje personaje) {

        return personajeRepository.save(personaje);

    }

    @Override
    public List<Personaje> findByMovieId(Long idMovie) {

        return personajeRepository.findByMoviesId(idMovie);
    }

    private boolean checkMoviesExistence(List<Long> moviesIds) {

        return peliculaRepository.findAll().stream().map(Pelicula::getId).collect(Collectors.toList()).containsAll(moviesIds);

    }

    @Override
    public void addMovies(Long characterId, List<Long> moviesIds) {

        Personaje personaje = findById(characterId);

        if (checkMoviesExistence(moviesIds)) {

            peliculaRepository.findAllById(moviesIds).forEach(pelicula -> personaje.getPelicula().add(pelicula));

        } else {

            throw new ResourceNotFoundException("Make sure all movies you want to add to the character already exist on the server");

        }

        personajeRepository.save(personaje);

    }

    @Override
    public void removeMovies(Long characterId, List<Long> moviesIds) {

        Personaje personaje = findById(characterId);

        personaje.getPelicula().removeIf(pelicula -> moviesIds.contains(pelicula.getId()));

        personajeRepository.save(personaje);

    }
    public Personaje addPersonaje(Personaje personaje) {
        personaje.setId(UUID.randomUUID().toString());
        return personajeRepository.save(personaje);
    }

    public Personaje updatePersonaje(Personaje personaje) {
        return personajeRepository.save(personaje);
    }


}
