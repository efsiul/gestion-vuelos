package com.sitas.gestionvuelos.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tripulacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTripulante;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String rol;

    // Getters y setters
    // (Aquí irían los getters y setters)
}
