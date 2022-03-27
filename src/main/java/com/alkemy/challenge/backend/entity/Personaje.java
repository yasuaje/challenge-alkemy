package com.alkemy.challenge.backend.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "personaje")
public class Personaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personaje_id", nullable = false)
    private Long id;
    private String imagen;
    private String nombre;
    private int edad;
    private double peso;
    private String historia;

    @JoinTable(name = "personaje_pelicula",
            joinColumns = @JoinColumn(name = "personaje_id"),
            inverseJoinColumns = @JoinColumn(name = "pelicula_id"))
    @ManyToMany
    private List<Pelicula> pelicula;

    public void setId(String toString) {
    }
}