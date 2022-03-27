package com.alkemy.challenge.backend.controller;

import com.alkemy.challenge.backend.entity.Pelicula;
import com.alkemy.challenge.backend.service.implement.GeneroService;
import com.alkemy.challenge.backend.service.implement.PeliculaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RequestMapping("/movies")
@RestController
public class PeliculaController {
    private final PeliculaService peliculaService;
    private final GeneroService generoService;
    public PeliculaController(PeliculaService peliculaService, GeneroService generoService) {
        this.peliculaService = peliculaService;
        this.generoService = generoService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<Pelicula>> getAll() {
        List<Pelicula> peliculas = peliculaService.getAll();
        return new ResponseEntity<>(peliculas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pelicula> getPeliculaById (@PathVariable("id") Long id) {
        Pelicula pelicula = peliculaService.findById(id);
        return new ResponseEntity<>(pelicula, HttpStatus.OK);
    }
    @GetMapping(params="titulo")
    public List<Pelicula> findByNombre(@RequestParam("titulo") String titulo){
        return peliculaService.findByTitulo(titulo);
    }
    @GetMapping(params="order")
    public Iterable<Object[]> getByOrder(@RequestParam("order") String order){
        return peliculaService.getByOrder(order);
    }
    @GetMapping(value = "", params="id")
    public List<Pelicula> getByGenre(@RequestParam("id") Long id){
        return generoService.getMoviesByGenreId(id);
    }

    @DeleteMapping(path = "delete/{id}")
    public String delete(@PathVariable("id") Long id){
        try {
            peliculaService.delete(id);
            return "Character was deleted id: " + id;
        } catch (Exception e) {
            return "Character cannot deleted id: " + id;
        }
    }

    @PostMapping("save")
    public Pelicula save(@RequestParam("file") MultipartFile image, @ModelAttribute Pelicula pelicula){
        if(!image.isEmpty()){
            Path imagesPath = Paths.get("src//main//resources//static//images");
            String absolutPath = imagesPath.toFile().getAbsolutePath();
            try {
                byte[] bytes = image.getBytes();
                Path route = Paths.get(absolutPath + image.getOriginalFilename());
                Files.write(route, bytes);
                pelicula.setImagen(image.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return peliculaService.save(pelicula);
    }
}
