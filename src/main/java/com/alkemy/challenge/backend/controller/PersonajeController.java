package com.alkemy.challenge.backend.controller;

import com.alkemy.challenge.backend.entity.Personaje;
import com.alkemy.challenge.backend.service.implement.PersonajeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class PersonajeController {
    private final PersonajeService personajeService;
    public PersonajeController(PersonajeService personajeService) {
        this.personajeService = personajeService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<Personaje>> getAll() {
        List<Personaje> personajes = personajeService.getAll();
        return new ResponseEntity<>(personajes, HttpStatus.OK);

    }
    @GetMapping("/{id}")
    public ResponseEntity<Personaje> getPersonajeById (@PathVariable("id") Long id) {
        Personaje personaje = personajeService.findById(id);
        return new ResponseEntity<>(personaje, HttpStatus.OK);
    }
    @GetMapping(params="nombre")
    public List<Personaje> findByNombre(@RequestParam("nombre") String nombre){
        return personajeService.findByName(nombre);
    }
    @GetMapping(params="edad")
    public List<Personaje> findByEdad(@RequestParam("edad") Integer edad){
        return personajeService.findByAge(edad);
    }

    @PostMapping("/add")
    public ResponseEntity<Personaje> addPersonaje(@RequestBody Personaje personaje) {
        Personaje newPersonaje = personajeService.addPersonaje(personaje);
        return new ResponseEntity<>(newPersonaje, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Personaje> updatePersonaje(@RequestBody Personaje personaje) {
        Personaje updatePersonaje = personajeService.updatePersonaje(personaje);
        return new ResponseEntity<>(updatePersonaje, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePersonaje(@PathVariable("id") Long id) {
        personajeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
