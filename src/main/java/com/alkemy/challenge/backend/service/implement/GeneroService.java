package com.alkemy.challenge.backend.service.implement;

import com.alkemy.challenge.backend.entity.Genero;
import com.alkemy.challenge.backend.entity.Pelicula;
import com.alkemy.challenge.backend.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class GeneroService {
    @Autowired
    private GeneroRepository generoRepository;

    public Genero save(Genero genero){
        return generoRepository.save(genero);
    }
    public boolean delete(Long id){
        try{
            generoRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }

    }
    public Optional<Genero> findById(Long id) {
        return generoRepository.findById(id);
    }
    public List<Pelicula> getMoviesByGenreId(Long id) {
        Genero genero = generoRepository.getById(id);
        if(genero != null){
            List<Pelicula> peliculas = genero.getPelicula();
            return peliculas;
        }else{
            return null;
        }
    }
}
