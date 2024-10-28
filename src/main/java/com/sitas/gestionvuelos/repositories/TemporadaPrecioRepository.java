package com.sitas.gestionvuelos.repositories;

import com.sitas.gestionvuelos.entities.TemporadaPrecio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemporadaPrecioRepository extends JpaRepository<TemporadaPrecio, Long> {
    // Métodos personalizados si es necesario
}
