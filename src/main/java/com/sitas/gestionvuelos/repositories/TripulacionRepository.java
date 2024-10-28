package com.sitas.gestionvuelos.repositories;

import com.sitas.gestionvuelos.entities.Tripulacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripulacionRepository extends JpaRepository<Tripulacion, Long> {
    // Métodos personalizados si es necesario
}
