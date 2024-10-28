package com.sitas.gestionvuelos.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TemporadaPrecio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTemporada;

    @Column(nullable = false)
    private String descripcionTemporada;

    @ManyToOne
    @JoinColumn(name = "numero_vuelo", nullable = false)
    private Vuelo vuelo;

    @Column(nullable = false)
    private BigDecimal precioTemporada;

    // Getters y setters
    // (Aquí irían los getters y setters)
}
