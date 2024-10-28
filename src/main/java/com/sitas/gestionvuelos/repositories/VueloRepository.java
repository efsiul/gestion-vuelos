package com.sitas.gestionvuelos.repositories;

import com.sitas.gestionvuelos.entities.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VueloRepository extends JpaRepository<Vuelo, String> {
    // Métodos personalizados si es necesario
}
