package com.sitas.gestionvuelos.repositories;

import com.sitas.gestionvuelos.entities.Aeronave;
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
class VueloRepositoryTest {

    @Autowired
    private VueloRepository vueloRepository;

    @Autowired
    private AeronaveRepository aeronaveRepository;

    @Test
    void testGuardarYEncontrarVuelo() {
        // Crear una aeronave para asociarla con el vuelo
        Aeronave aeronave = new Aeronave("Boeing 737", 200, "3-3");
        Aeronave savedAeronave = aeronaveRepository.save(aeronave);

        // Crear un vuelo y asignar valores válidos
        Vuelo vuelo = new Vuelo();
        vuelo.setNumeroVuelo("V123");
        vuelo.setTipoVuelo("Comercial");
        vuelo.setCiudadOrigen("Bogotá");
        vuelo.setCiudadDestino("Medellín");
        vuelo.setFechaSalida(LocalDate.now());
        vuelo.setFechaLlegada(LocalDate.now().plusDays(1));
        vuelo.setHoraSalida(LocalTime.of(10, 0));
        vuelo.setHoraLlegada(LocalTime.of(12, 0));
        vuelo.setPrecio(BigDecimal.valueOf(200.00));
        vuelo.setPorcentajeImpuestos(BigDecimal.valueOf(10.00));  // Asignar porcentaje de impuestos
        vuelo.setSobretasa(BigDecimal.valueOf(5.00));  // Asignar sobretasa
        vuelo.setEstadoVuelo("Programado");  // Asignar un valor a estadoVuelo
        vuelo.setAeronave(savedAeronave);  // Asociar la aeronave

        // Guardar el vuelo en la base de datos
        Vuelo savedVuelo = vueloRepository.save(vuelo);

        // Verificar que se guardó correctamente
        assertNotNull(savedVuelo);
        assertEquals("V123", savedVuelo.getNumeroVuelo());

        // Buscar el vuelo por su número
        Optional<Vuelo> foundVuelo = vueloRepository.findById(savedVuelo.getNumeroVuelo());
        assertTrue(foundVuelo.isPresent());
        assertEquals("Comercial", foundVuelo.get().getTipoVuelo());
    }

    @Test
    void testEliminarVuelo() {
        // Crear una aeronave para asociarla con el vuelo
        Aeronave aeronave = new Aeronave("Airbus A320", 180, "3-3");
        Aeronave savedAeronave = aeronaveRepository.save(aeronave);

        // Crear un vuelo y asignar valores válidos
        Vuelo vuelo = new Vuelo();
        vuelo.setNumeroVuelo("V124");
        vuelo.setTipoVuelo("Privado");
        vuelo.setCiudadOrigen("Cali");
        vuelo.setCiudadDestino("Cartagena");
        vuelo.setFechaSalida(LocalDate.now());
        vuelo.setFechaLlegada(LocalDate.now().plusDays(1));
        vuelo.setHoraSalida(LocalTime.of(10, 0));
        vuelo.setHoraLlegada(LocalTime.of(12, 0));
        vuelo.setEstadoVuelo("Programado");
        vuelo.setPrecio(BigDecimal.valueOf(250.00));
        vuelo.setPorcentajeImpuestos(BigDecimal.valueOf(15.00));  // Asignar porcentaje de impuestos
        vuelo.setSobretasa(BigDecimal.valueOf(5.00));  // Asignar sobretasa
        vuelo.setAeronave(savedAeronave);  // Asociar la aeronave
        Vuelo savedVuelo = vueloRepository.save(vuelo);

        // Eliminar el vuelo
        vueloRepository.deleteById(savedVuelo.getNumeroVuelo());

        // Verificar que ya no existe
        Optional<Vuelo> deletedVuelo = vueloRepository.findById(savedVuelo.getNumeroVuelo());
        assertFalse(deletedVuelo.isPresent());
    }
}
