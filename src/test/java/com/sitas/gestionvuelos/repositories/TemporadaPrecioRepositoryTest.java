package com.sitas.gestionvuelos.repositories;

import com.sitas.gestionvuelos.entities.Aeronave;
import com.sitas.gestionvuelos.entities.TemporadaPrecio;
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
class TemporadaPrecioRepositoryTest {

    @Autowired
    private TemporadaPrecioRepository temporadaPrecioRepository;

    @Autowired
    private VueloRepository vueloRepository;

    @Autowired
    private AeronaveRepository aeronaveRepository;

    @Test
    void testGuardarYEncontrarTemporadaPrecio() {
        Aeronave aeronave = new Aeronave("Boeing 737", 180, "3-3");
        Aeronave savedAeronave = aeronaveRepository.save(aeronave);

        Vuelo vuelo = new Vuelo();
        vuelo.setNumeroVuelo("V003");
        vuelo.setTipoVuelo("Comercial");
        vuelo.setCiudadOrigen("Lima");
        vuelo.setCiudadDestino("Cusco");
        vuelo.setEstadoVuelo("Programado");
        vuelo.setFechaSalida(LocalDate.now());
        vuelo.setFechaLlegada(LocalDate.now().plusDays(1));
        vuelo.setHoraSalida(LocalTime.of(10, 0));
        vuelo.setHoraLlegada(LocalTime.of(12, 0));
        vuelo.setPrecio(BigDecimal.valueOf(200.00));
        vuelo.setPorcentajeImpuestos(BigDecimal.valueOf(10.00));
        vuelo.setSobretasa(BigDecimal.valueOf(5.00));
        vuelo.setAeronave(savedAeronave);
        Vuelo savedVuelo = vueloRepository.save(vuelo);

        TemporadaPrecio temporadaPrecio = new TemporadaPrecio();
        temporadaPrecio.setDescripcionTemporada("Temporada Alta");
        temporadaPrecio.setPrecioTemporada(BigDecimal.valueOf(800.00));
        temporadaPrecio.setVuelo(savedVuelo);

        TemporadaPrecio savedTemporadaPrecio = temporadaPrecioRepository.save(temporadaPrecio);

        assertNotNull(savedTemporadaPrecio);
        assertEquals("Temporada Alta", savedTemporadaPrecio.getDescripcionTemporada());

        Optional<TemporadaPrecio> foundTemporadaPrecio = temporadaPrecioRepository.findById(savedTemporadaPrecio.getIdTemporada());
        assertTrue(foundTemporadaPrecio.isPresent());

        assertTrue(BigDecimal.valueOf(800.00).compareTo(foundTemporadaPrecio.get().getPrecioTemporada()) == 0);
    }
}
