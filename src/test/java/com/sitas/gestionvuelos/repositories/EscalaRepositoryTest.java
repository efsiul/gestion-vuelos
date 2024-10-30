package com.sitas.gestionvuelos.repositories;

import com.sitas.gestionvuelos.entities.Aeronave;
import com.sitas.gestionvuelos.entities.Escala;
import com.sitas.gestionvuelos.entities.Vuelo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class EscalaRepositoryTest {

    @Autowired
    private EscalaRepository escalaRepository;

    @Autowired
    private VueloRepository vueloRepository;

    @Autowired
    private AeronaveRepository aeronaveRepository;

    @Test
    void testGuardarEscala() {
        // Primero creamos y guardamos una aeronave
        Aeronave aeronave = new Aeronave("Boeing 737", 150, "3-3");
        aeronaveRepository.save(aeronave);

        // Luego creamos un vuelo asociado a esa aeronave
        Vuelo vuelo = new Vuelo("V001", "Comercial", "Bogotá", "Medellín", aeronave,
                LocalDate.now(), LocalDate.now().plusDays(1), LocalTime.of(10, 0), LocalTime.of(12, 0),
                BigDecimal.valueOf(300.00), BigDecimal.valueOf(10.0), BigDecimal.valueOf(5.0));
        vueloRepository.save(vuelo);

        // Ahora podemos crear y guardar la escala
        Escala escala = new Escala(null, vuelo, "Aeropuerto Internacional El Dorado", 60);
        Escala savedEscala = escalaRepository.save(escala);

        assertNotNull(savedEscala.getIdEscala());
        assertEquals("Aeropuerto Internacional El Dorado", savedEscala.getAeropuertoEscala());
        assertEquals(60, savedEscala.getDuracionEscala());
    }

    @Test
    void testBuscarEscalaPorId() {
        // Primero creamos y guardamos una aeronave
        Aeronave aeronave = new Aeronave("Airbus A320", 180, "3-3");
        aeronaveRepository.save(aeronave);

        // Creamos un vuelo asociado a la aeronave guardada
        Vuelo vuelo = new Vuelo("V002", "Comercial", "Cali", "Cartagena", aeronave,
                LocalDate.now(), LocalDate.now().plusDays(1), LocalTime.of(15, 0), LocalTime.of(17, 0),
                BigDecimal.valueOf(180.00), BigDecimal.valueOf(15.0), BigDecimal.valueOf(8.0));
        vueloRepository.save(vuelo);

        // Ahora podemos crear y guardar la escala
        Escala escala = new Escala(null, vuelo, "Aeropuerto Internacional Rafael Núñez", 90);
        Escala savedEscala = escalaRepository.save(escala);

        Optional<Escala> foundEscala = escalaRepository.findById(savedEscala.getIdEscala());

        assertTrue(foundEscala.isPresent());
        assertEquals("Aeropuerto Internacional Rafael Núñez", foundEscala.get().getAeropuertoEscala());
    }

    @Test
    void testActualizarEscala() {
        // Crear y guardar la aeronave primero
        Aeronave aeronave = new Aeronave("Embraer E190", 100, "2-2");
        aeronaveRepository.save(aeronave);

        // Crear y guardar el vuelo con la aeronave asignada
        Vuelo vuelo = new Vuelo("V003", "Carga", "Miami", "Bogotá", aeronave,
                LocalDate.now(), LocalDate.now().plusDays(2), LocalTime.of(8, 0), LocalTime.of(14, 0),
                BigDecimal.valueOf(200.00), BigDecimal.valueOf(12.0), BigDecimal.valueOf(6.0));
        vueloRepository.save(vuelo);

        Escala escala = new Escala(null, vuelo, "Aeropuerto Internacional de Miami", 120);
        Escala savedEscala = escalaRepository.save(escala);

        // Actualizar los datos de la escala
        savedEscala.setAeropuertoEscala("Aeropuerto Internacional El Dorado");
        savedEscala.setDuracionEscala(60);
        escalaRepository.save(savedEscala);

        Optional<Escala> updatedEscala = escalaRepository.findById(savedEscala.getIdEscala());
        assertTrue(updatedEscala.isPresent());
        assertEquals("Aeropuerto Internacional El Dorado", updatedEscala.get().getAeropuertoEscala());
        assertEquals(60, updatedEscala.get().getDuracionEscala());
    }

    @Test
    void testEliminarEscala() {
        // Crear y guardar la aeronave primero
        Aeronave aeronave = new Aeronave("Cessna 172", 4, "1-1");
        aeronaveRepository.save(aeronave);

        // Crear y guardar el vuelo con la aeronave asignada
        Vuelo vuelo = new Vuelo("V004", "Privado", "Lima", "Buenos Aires", aeronave,
                LocalDate.now(), LocalDate.now().plusDays(3), LocalTime.of(12, 0), LocalTime.of(18, 0),
                BigDecimal.valueOf(250.00), BigDecimal.valueOf(20.0), BigDecimal.valueOf(10.0));
        vueloRepository.save(vuelo);

        Escala escala = new Escala(null, vuelo, "Aeropuerto Internacional Jorge Chávez", 45);
        Escala savedEscala = escalaRepository.save(escala);

        // Eliminar la escala
        escalaRepository.deleteById(savedEscala.getIdEscala());

        // Verificar que la escala ya no exista en el repositorio
        Optional<Escala> deletedEscala = escalaRepository.findById(savedEscala.getIdEscala());
        assertFalse(deletedEscala.isPresent());
    }
}
