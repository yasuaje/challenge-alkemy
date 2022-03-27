package com.alkemy.challenge.backend.service.implement;

import com.alkemy.challenge.backend.entity.Genero;
import com.alkemy.challenge.backend.entity.Pelicula;
import com.alkemy.challenge.backend.exception.ResourceNotFoundException;
import com.alkemy.challenge.backend.repository.GeneroRepository;
import com.alkemy.challenge.backend.repository.PeliculaRepository;
import com.alkemy.challenge.backend.service.IPeliculaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public abstract class PeliculaService implements IPeliculaService {

    private final PeliculaRepository peliculaRepository;
    private final GeneroRepository generoRepository;

    @Override
    public List<Pelicula> getAll() {

        return peliculaRepository.findAll();

    }

    @Override
    public List<Pelicula> findAllOrderByCreationDate(String order) {

        if(order.equalsIgnoreCase("ASC")) {

            return peliculaRepository.findAllByOrderByCreationDateAsc();

        } else if (order.equalsIgnoreCase("DESC")) {

            return peliculaRepository.findAllByOrderByCreationDateDesc();

        }

        return null;

    }

    @Override
    public Pelicula findById(Long movieId) {

        return peliculaRepository.findById(movieId).orElseThrow(() -> new ResourceNotFoundException("No Movie with ID : " + movieId));

    }

    @Override
    public List<Pelicula> findByTitle(String titulo) {

        return peliculaRepository.findByTitulo(titulo);

    }

    @Override
    public void delete(Long id){

        peliculaRepository.delete(findById(id));

    }

    @Override
    public Pelicula save(Pelicula pelicula) {


        return peliculaRepository.save(pelicula);

    }

    @Override
    public List<Pelicula> findByGenreId(Long idGenre) {

        return peliculaRepository.findByGenresId(idGenre);

    }

    private boolean checkGenresExistence(List<Long> genresIds) {

        return generoRepository.findAll().stream().map(Genero::getId).collect(Collectors.toList()).containsAll(genresIds);

    }

    @Override
    public List<Genero> getGenres(Long id) {

        return findById(id).getGenero();

    }


    @Override
    public void addGenres(Long movieId, List<Long> genresIds) {

        Pelicula pelicula = findById(movieId);

        if (checkGenresExistence(genresIds)) {

            generoRepository.findAllById(genresIds).forEach(genre -> pelicula.getGenero().add(genre));

        } else {

            throw new ResourceNotFoundException("Make sure all movies you want to add to the character already exist on the server");

        }

        peliculaRepository.save(pelicula);

    }

    @Override
    public void removeGenres(Long movieId, List<Long> genresIds) {

        Pelicula pelicula = findById(movieId);

        pelicula.getGenero().removeIf(genre -> genresIds.contains(genre.getId()));

        peliculaRepository.save(pelicula);

    }
    public Iterable<Object[]> getByOrder(String order){
        if(order.equals("ASC")){
            return peliculaRepository.getAllByOrderASC();
        }else if(order.equals("DESC")){
            return peliculaRepository.getAllByOrderDESC();
        }else{
            return peliculaRepository.getAll();
        }
    }

}
