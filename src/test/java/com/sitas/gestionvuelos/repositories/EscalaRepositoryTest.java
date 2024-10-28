package com.sitas.gestionvuelos.repositories;

import com.sitas.gestionvuelos.entities.Aeronave;
import com.sitas.gestionvuelos.entities.Escala;
import com.sitas.gestionvuelos.entities.Vuelo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class EscalaRepositoryTest {

    @Autowired
    private EscalaRepository escalaRepository;

    @Autowired
    private VueloRepository vueloRepository;

    @Autowired
    private AeronaveRepository aeronaveRepository;

    @Test
    void testGuardarYEncontrarEscala() {
        // Crear una aeronave para asociarla con el vuelo
        Aeronave aeronave = new Aeronave("Boeing 747", 300, "3-3");
        Aeronave savedAeronave = aeronaveRepository.save(aeronave);

        // Crear un vuelo y asignar valores válidos
        Vuelo vuelo = new Vuelo();
        vuelo.setNumeroVuelo("V001");  // Asignar manualmente el ID
        vuelo.setTipoVuelo("Comercial");
        vuelo.setCiudadOrigen("Bogotá");
        vuelo.setCiudadDestino("Medellín");
        vuelo.setEstadoVuelo("Programado");
        vuelo.setFechaSalida(LocalDate.now());
        vuelo.setFechaLlegada(LocalDate.now().plusDays(1));
        vuelo.setHoraSalida(LocalTime.of(10, 0));
        vuelo.setHoraLlegada(LocalTime.of(12, 0));
        vuelo.setPrecio(BigDecimal.valueOf(200.00));
        vuelo.setPorcentajeImpuestos(BigDecimal.valueOf(10.00));  // Asignar porcentaje de impuestos
        vuelo.setSobretasa(BigDecimal.valueOf(5.00));  // Asignar sobretasa
        vuelo.setAeronave(savedAeronave);  // Asociar la aeronave
        Vuelo savedVuelo = vueloRepository.save(vuelo);

        // Crear una escala asociada al vuelo
        Escala escala = new Escala();
        escala.setAeropuertoEscala("Aeropuerto Internacional");
        escala.setDuracionEscala(120);
        escala.setVuelo(savedVuelo);  // Asignar el vuelo a la escala

        // Guardar la escala en la base de datos
        Escala savedEscala = escalaRepository.save(escala);

        // Verificar que se guardó correctamente
        assertNotNull(savedEscala);
        assertEquals("Aeropuerto Internacional", savedEscala.getAeropuertoEscala());

        // Buscar la escala por su ID
        Optional<Escala> foundEscala = escalaRepository.findById(savedEscala.getIdEscala());
        assertTrue(foundEscala.isPresent());
        assertEquals(120, foundEscala.get().getDuracionEscala());
    }

    @Test
    void testEliminarEscala() {
        // Crear una aeronave para asociarla con el vuelo
        Aeronave aeronave = new Aeronave("Airbus A320", 200, "3-3");
        Aeronave savedAeronave = aeronaveRepository.save(aeronave);

        // Crear un vuelo y asignar valores válidos
        Vuelo vuelo = new Vuelo();
        vuelo.setNumeroVuelo("V002");  // Asignar manualmente el ID
        vuelo.setTipoVuelo("Privado");
        vuelo.setCiudadOrigen("Cali");
        vuelo.setCiudadDestino("Cartagena");
        vuelo.setEstadoVuelo("Programado");
        vuelo.setFechaSalida(LocalDate.now());
        vuelo.setFechaLlegada(LocalDate.now().plusDays(1));
        vuelo.setHoraSalida(LocalTime.of(10, 0));
        vuelo.setHoraLlegada(LocalTime.of(12, 0));
        vuelo.setPrecio(BigDecimal.valueOf(250.00));
        vuelo.setPorcentajeImpuestos(BigDecimal.valueOf(15.00));  // Asignar porcentaje de impuestos
        vuelo.setSobretasa(BigDecimal.valueOf(5.00));  // Asignar sobretasa
        vuelo.setAeronave(savedAeronave);  // Asociar la aeronave
        Vuelo savedVuelo = vueloRepository.save(vuelo);

        // Crear una nueva escala asociada al vuelo
        Escala escala = new Escala();
        escala.setAeropuertoEscala("Aeropuerto Nacional");
        escala.setDuracionEscala(60);
        escala.setVuelo(savedVuelo);  // Asignar el vuelo a la escala

        Escala savedEscala = escalaRepository.save(escala);

        // Eliminar la escala
        escalaRepository.deleteById(savedEscala.getIdEscala());

        // Verificar que ya no existe
        Optional<Escala> deletedEscala = escalaRepository.findById(savedEscala.getIdEscala());
        assertFalse(deletedEscala.isPresent());
    }
}
