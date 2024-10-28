package com.sitas.gestionvuelos.repositories;

import com.sitas.gestionvuelos.entities.Escala;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EscalaRepository extends JpaRepository<Escala, Long> {
    // Métodos personalizados si es necesario
}
