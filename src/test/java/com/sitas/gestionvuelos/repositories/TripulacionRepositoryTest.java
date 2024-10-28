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
        // Crear un miembro de tripulación
        Tripulacion tripulacion = new Tripulacion();
        tripulacion.setNombre("John Doe");
        tripulacion.setRol("Piloto");

        // Guardar en la base de datos
        Tripulacion savedTripulacion = tripulacionRepository.save(tripulacion);

        // Verificar que se guardó correctamente
        assertNotNull(savedTripulacion);
        assertEquals("John Doe", savedTripulacion.getNombre());

        // Buscar la tripulación por su ID
        Optional<Tripulacion> foundTripulacion = tripulacionRepository.findById(savedTripulacion.getIdTripulante());
        assertTrue(foundTripulacion.isPresent());
        assertEquals("Piloto", foundTripulacion.get().getRol());
    }

    @Test
    void testEliminarTripulacion() {
        // Crear una nueva tripulación
        Tripulacion tripulacion = new Tripulacion();
        tripulacion.setNombre("Jane Doe");
        tripulacion.setRol("Copiloto");
        Tripulacion savedTripulacion = tripulacionRepository.save(tripulacion);

        // Eliminar la tripulación
        tripulacionRepository.deleteById(savedTripulacion.getIdTripulante());

        // Verificar que ya no existe
        Optional<Tripulacion> deletedTripulacion = tripulacionRepository.findById(savedTripulacion.getIdTripulante());
        assertFalse(deletedTripulacion.isPresent());
    }
}
