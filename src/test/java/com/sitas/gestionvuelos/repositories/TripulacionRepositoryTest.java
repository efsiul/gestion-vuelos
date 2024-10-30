package com.sitas.gestionvuelos.repositories;

import com.sitas.gestionvuelos.entities.Tripulacion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class TripulacionRepositoryTest {

    @Autowired
    private TripulacionRepository tripulacionRepository;

    @Test
    void testGuardarTripulacion() {
        Tripulacion tripulante = new Tripulacion(null, "Juan Pérez", "Piloto");
        Tripulacion savedTripulante = tripulacionRepository.save(tripulante);

        assertNotNull(savedTripulante.getIdTripulante());
        assertEquals("Juan Pérez", savedTripulante.getNombre());
        assertEquals("Piloto", savedTripulante.getRol());
    }

    @Test
    void testBuscarTripulacionPorId() {
        Tripulacion tripulante = new Tripulacion(null, "María Gómez", "Copiloto");
        Tripulacion savedTripulante = tripulacionRepository.save(tripulante);

        Optional<Tripulacion> foundTripulante = tripulacionRepository.findById(savedTripulante.getIdTripulante());

        assertTrue(foundTripulante.isPresent());
        assertEquals("María Gómez", foundTripulante.get().getNombre());
        assertEquals("Copiloto", foundTripulante.get().getRol());
    }

    @Test
    void testActualizarTripulacion() {
        Tripulacion tripulante = new Tripulacion(null, "Carlos Mendoza", "Asistente de Vuelo");
        Tripulacion savedTripulante = tripulacionRepository.save(tripulante);

        // Actualizamos el rol del tripulante
        savedTripulante.setRol("Jefe de Cabina");
        tripulacionRepository.save(savedTripulante);

        Optional<Tripulacion> updatedTripulante = tripulacionRepository.findById(savedTripulante.getIdTripulante());
        assertTrue(updatedTripulante.isPresent());
        assertEquals("Jefe de Cabina", updatedTripulante.get().getRol());
    }

    @Test
    void testEliminarTripulacion() {
        Tripulacion tripulante = new Tripulacion(null, "Laura Martínez", "Azafata");
        Tripulacion savedTripulante = tripulacionRepository.save(tripulante);

        // Eliminamos el tripulante
        tripulacionRepository.deleteById(savedTripulante.getIdTripulante());

        // Verificamos que el tripulante ya no exista en el repositorio
        Optional<Tripulacion> deletedTripulante = tripulacionRepository.findById(savedTripulante.getIdTripulante());
        assertFalse(deletedTripulante.isPresent());
    }
}
