package com.sitas.gestionvuelos.repositories;

import com.sitas.gestionvuelos.entities.Aeronave;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class AeronaveRepositoryTest {

    @Autowired
    private AeronaveRepository aeronaveRepository;

    @Test
    void testGuardarAeronave() {
        Aeronave aeronave = new Aeronave("Boeing 737", 150, "3-3");
        Aeronave savedAeronave = aeronaveRepository.save(aeronave);

        assertNotNull(savedAeronave.getIdAeronave());
        assertEquals("Boeing 737", savedAeronave.getTipoAvion());
        assertEquals(150, savedAeronave.getCantidadMaxAsientos());
        assertEquals("3-3", savedAeronave.getDistribucionAsientos());
    }

    @Test
    void testBuscarAeronavePorId() {
        Aeronave aeronave = new Aeronave("Airbus A320", 180, "3-3");
        Aeronave savedAeronave = aeronaveRepository.save(aeronave);

        Optional<Aeronave> foundAeronave = aeronaveRepository.findById(savedAeronave.getIdAeronave());

        assertTrue(foundAeronave.isPresent());
        assertEquals("Airbus A320", foundAeronave.get().getTipoAvion());
    }

    @Test
    void testActualizarAeronave() {
        Aeronave aeronave = new Aeronave("Embraer E190", 100, "2-2");
        Aeronave savedAeronave = aeronaveRepository.save(aeronave);

        // Actualizamos los datos de la aeronave
        savedAeronave.setTipoAvion("Embraer E195");
        savedAeronave.setCantidadMaxAsientos(110);
        aeronaveRepository.save(savedAeronave);

        // Verificamos que se hayan actualizado correctamente
        Optional<Aeronave> updatedAeronave = aeronaveRepository.findById(savedAeronave.getIdAeronave());
        assertTrue(updatedAeronave.isPresent());
        assertEquals("Embraer E195", updatedAeronave.get().getTipoAvion());
        assertEquals(110, updatedAeronave.get().getCantidadMaxAsientos());
    }

    @Test
    void testEliminarAeronave() {
        Aeronave aeronave = new Aeronave("Cessna 172", 4, "1-1");
        Aeronave savedAeronave = aeronaveRepository.save(aeronave);

        // Eliminamos la aeronave
        aeronaveRepository.deleteById(savedAeronave.getIdAeronave());

        // Verificamos que la aeronave ya no exista en el repositorio
        Optional<Aeronave> deletedAeronave = aeronaveRepository.findById(savedAeronave.getIdAeronave());
        assertFalse(deletedAeronave.isPresent());
    }
}
