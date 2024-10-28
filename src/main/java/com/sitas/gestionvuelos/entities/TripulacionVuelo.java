package com.sitas.gestionvuelos.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TripulacionVuelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_tripulante", nullable = false)
    private Tripulacion tripulante;

    @ManyToOne
    @JoinColumn(name = "numero_vuelo", nullable = false)
    private Vuelo vuelo;

    // Getters y setters
    // (Aquí irían los getters y setters)
}
