package com.sitas.gestionvuelos.repositories;

import com.sitas.gestionvuelos.entities.Aeronave;
import com.sitas.gestionvuelos.entities.TemporadaPrecio;
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
class TemporadaPrecioRepositoryTest {

    @Autowired
    private TemporadaPrecioRepository temporadaPrecioRepository;

    @Autowired
    private VueloRepository vueloRepository;

    @Autowired
    private AeronaveRepository aeronaveRepository;

    @Test
    void testGuardarTemporadaPrecio() {
        // Guardamos una aeronave primero
        Aeronave aeronave = new Aeronave("Boeing 747", 400, "3-4-3");
        aeronaveRepository.save(aeronave);

        // Luego creamos un vuelo que use la aeronave guardada
        Vuelo vuelo = new Vuelo("V005", "Internacional", "Bogotá", "Londres", aeronave,
                LocalDate.now(), LocalDate.now().plusDays(2), LocalTime.of(14, 0), LocalTime.of(6, 0),
                BigDecimal.valueOf(1000.00), BigDecimal.valueOf(15.0), BigDecimal.valueOf(10.0));
        vueloRepository.save(vuelo);

        TemporadaPrecio temporada = new TemporadaPrecio(null, "Temporada Alta", vuelo, BigDecimal.valueOf(1200.00));
        TemporadaPrecio savedTemporada = temporadaPrecioRepository.save(temporada);

        assertNotNull(savedTemporada.getIdTemporada());
        assertEquals("Temporada Alta", savedTemporada.getDescripcionTemporada());
        assertEquals(BigDecimal.valueOf(1200.00), savedTemporada.getPrecioTemporada());
    }

    @Test
    void testBuscarTemporadaPrecioPorId() {
        // Crear y guardar la aeronave primero
        Aeronave aeronave = new Aeronave("Airbus A380", 500, "3-4-3");
        aeronaveRepository.save(aeronave);

        // Crear y guardar el vuelo con la aeronave asignada
        Vuelo vuelo = new Vuelo("V006", "Nacional", "Medellín", "Cartagena", aeronave,
                LocalDate.now(), LocalDate.now().plusDays(1), LocalTime.of(10, 0), LocalTime.of(12, 0),
                BigDecimal.valueOf(300.00), BigDecimal.valueOf(10.0), BigDecimal.valueOf(5.0));
        vueloRepository.save(vuelo);

        TemporadaPrecio temporada = new TemporadaPrecio(null, "Temporada Baja", vuelo, BigDecimal.valueOf(250.00));
        TemporadaPrecio savedTemporada = temporadaPrecioRepository.save(temporada);

        Optional<TemporadaPrecio> foundTemporada = temporadaPrecioRepository.findById(savedTemporada.getIdTemporada());

        assertTrue(foundTemporada.isPresent());
        assertEquals("Temporada Baja", foundTemporada.get().getDescripcionTemporada());
        assertEquals(BigDecimal.valueOf(250.00), foundTemporada.get().getPrecioTemporada());
    }

    @Test
    void testActualizarTemporadaPrecio() {
        // Crear y guardar la aeronave primero
        Aeronave aeronave = new Aeronave("Boeing 747", 400, "3-4-3");
        aeronaveRepository.save(aeronave);

        // Crear y guardar el vuelo con la aeronave asignada
        Vuelo vuelo = new Vuelo("V007", "Carga", "Miami", "Buenos Aires", aeronave,
                LocalDate.now(), LocalDate.now().plusDays(3), LocalTime.of(16, 0), LocalTime.of(20, 0),
                BigDecimal.valueOf(600.00), BigDecimal.valueOf(12.0), BigDecimal.valueOf(6.0));
        vueloRepository.save(vuelo);

        TemporadaPrecio temporada = new TemporadaPrecio(null, "Temporada Media", vuelo, BigDecimal.valueOf(550.00));
        TemporadaPrecio savedTemporada = temporadaPrecioRepository.save(temporada);

        // Actualizamos la temporada y el precio
        savedTemporada.setDescripcionTemporada("Temporada Festiva");
        savedTemporada.setPrecioTemporada(BigDecimal.valueOf(650.00));
        temporadaPrecioRepository.save(savedTemporada);

        Optional<TemporadaPrecio> updatedTemporada = temporadaPrecioRepository.findById(savedTemporada.getIdTemporada());
        assertTrue(updatedTemporada.isPresent());
        assertEquals("Temporada Festiva", updatedTemporada.get().getDescripcionTemporada());
        assertEquals(BigDecimal.valueOf(650.00), updatedTemporada.get().getPrecioTemporada());
    }

    @Test
    void testEliminarTemporadaPrecio() {
        // Crear y guardar la aeronave primero
        Aeronave aeronave = new Aeronave("Airbus A320", 180, "3-3");
        aeronaveRepository.save(aeronave);

        // Crear y guardar el vuelo con la aeronave asignada
        Vuelo vuelo = new Vuelo("V008", "Charter", "Ciudad de México", "Cancún", aeronave,
                LocalDate.now(), LocalDate.now().plusDays(1), LocalTime.of(9, 0), LocalTime.of(11, 0),
                BigDecimal.valueOf(500.00), BigDecimal.valueOf(18.0), BigDecimal.valueOf(8.0));
        vueloRepository.save(vuelo);

        TemporadaPrecio temporada = new TemporadaPrecio(null, "Temporada Especial", vuelo, BigDecimal.valueOf(700.00));
        TemporadaPrecio savedTemporada = temporadaPrecioRepository.save(temporada);

        // Eliminamos la temporada
        temporadaPrecioRepository.deleteById(savedTemporada.getIdTemporada());

        // Verificamos que la temporada ya no exista en el repositorio
        Optional<TemporadaPrecio> deletedTemporada = temporadaPrecioRepository.findById(savedTemporada.getIdTemporada());
        assertFalse(deletedTemporada.isPresent());
    }
}
