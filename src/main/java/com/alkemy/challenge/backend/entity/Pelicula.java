package com.alkemy.challenge.backend.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pelicula")
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pelicula_id", nullable = false)
    private Long id;
    private String imagen;
    private String titulo;
    private LocalDateTime fechaCreacion;
    private Integer clasificacion;

    @ManyToMany(mappedBy = "pelicula")
    private List<Personaje> personaje;

    @JoinTable(name = "pelicula_genero",
            joinColumns = @JoinColumn(name = "pelicula_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @ManyToMany
    private List<Genero> genero;

}