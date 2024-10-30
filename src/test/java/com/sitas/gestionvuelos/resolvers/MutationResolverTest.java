package com.sitas.gestionvuelos.resolvers;

import com.sitas.gestionvuelos.entities.Aeronave;
import com.sitas.gestionvuelos.entities.Vuelo;
import com.sitas.gestionvuelos.repositories.AeronaveRepository;
import com.sitas.gestionvuelos.repositories.VueloRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class MutationResolverTest {

    @Mock
    private AeronaveRepository aeronaveRepository;

    @Mock
    private VueloRepository vueloRepository;

    @InjectMocks
    private MutationResolver mutationResolver;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearAeronaveTest() {
        // Datos de prueba
        Aeronave aeronave = new Aeronave("Boeing 737", 150, "3-3");

        when(aeronaveRepository.save(any(Aeronave.class))).thenReturn(aeronave);

        Aeronave result = mutationResolver.crearAeronave("Boeing 737", 150, "3-3");

        assertEquals("Boeing 737", result.getTipoAvion());
        assertEquals(150, result.getCantidadMaxAsientos());
        assertEquals("3-3", result.getDistribucionAsientos());

        verify(aeronaveRepository, times(1)).save(any(Aeronave.class));
    }

    @Test
    void actualizarAeronaveTest() {
        Aeronave aeronave = new Aeronave("Airbus A320", 180, "3-3");
        aeronave.setIdAeronave(1L);

        when(aeronaveRepository.findById(1L)).thenReturn(Optional.of(aeronave));
        when(aeronaveRepository.save(any(Aeronave.class))).thenReturn(aeronave);

        Aeronave result = mutationResolver.actualizarAeronave(1L, "Airbus A320", 180, "3-3");

        assertEquals("Airbus A320", result.getTipoAvion());
        assertEquals(180, result.getCantidadMaxAsientos());

        verify(aeronaveRepository, times(1)).findById(1L);
        verify(aeronaveRepository, times(1)).save(aeronave);
    }

    @Test
    void crearVueloTest() {
        Aeronave aeronave = new Aeronave("Boeing 737", 150, "3-3");
        when(aeronaveRepository.findById(1L)).thenReturn(Optional.of(aeronave));

        Vuelo vuelo = new Vuelo("V001", "Comercial", "Bogotá", "Medellín", aeronave,
                LocalDate.now(), LocalDate.now().plusDays(1), LocalTime.now(), LocalTime.now().plusHours(1),
                BigDecimal.valueOf(100.00), BigDecimal.valueOf(10.0), BigDecimal.valueOf(5.0));

        when(vueloRepository.save(any(Vuelo.class))).thenReturn(vuelo);

        Vuelo result = mutationResolver.crearVuelo("V001", "Comercial", "Bogotá", "Medellín", 1L,
                "2023-12-10", "2023-12-11", "10:00", "11:00", 100.00f, 10.0f, 5.0f);

        assertEquals("V001", result.getNumeroVuelo());
        assertEquals("Comercial", result.getTipoVuelo());

        verify(aeronaveRepository, times(1)).findById(1L);
        verify(vueloRepository, times(1)).save(any(Vuelo.class));
    }
}
