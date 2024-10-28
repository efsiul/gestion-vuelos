package com.sitas.gestionvuelos.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Escala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEscala;

    @ManyToOne
    @JoinColumn(name = "numero_vuelo", nullable = false)
    private Vuelo vuelo;

    @Column(nullable = false)
    private String aeropuertoEscala;

    @Column(nullable = false)
    private int duracionEscala;

    // Getters y setters
    // (Aquí irían los getters y setters)
}
