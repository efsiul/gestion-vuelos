package com.sitas.gestionvuelos.repositories;

import com.sitas.gestionvuelos.entities.Tripulacion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class TripulacionRepositoryTest {

    @Autowired
    private TripulacionRepository tripulacionRepository;

    @Test
    void testGuardarYEncontrarTripulacion() {
        Tripulacion tripulacion = new Tripulacion();
        tripulacion.setNombre("John Doe");
        tripulacion.setRol("Piloto");

        Tripulacion savedTripulacion = tripulacionRepository.save(tripulacion);

        assertNotNull(savedTripulacion);
        assertEquals("John Doe", savedTripulacion.getNombre());

        Optional<Tripulacion> foundTripulacion = tripulacionRepository.findById(savedTripulacion.getIdTripulante());
        assertTrue(foundTripulacion.isPresent());
        assertEquals("Piloto", foundTripulacion.get().getRol());
    }

    @Test
    void testEliminarTripulacion() {
        Tripulacion tripulacion = new Tripulacion();
        tripulacion.setNombre("Jane Doe");
        tripulacion.setRol("Copiloto");
        Tripulacion savedTripulacion = tripulacionRepository.save(tripulacion);

        tripulacionRepository.deleteById(savedTripulacion.getIdTripulante());

        Optional<Tripulacion> deletedTripulacion = tripulacionRepository.findById(savedTripulacion.getIdTripulante());
        assertFalse(deletedTripulacion.isPresent());
    }
}
