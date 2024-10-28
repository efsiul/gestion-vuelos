package com.sitas.gestionvuelos.repositories;

import com.sitas.gestionvuelos.entities.Aeronave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AeronaveRepository extends JpaRepository<Aeronave, Long> {
    // Métodos personalizados si es necesario
}
