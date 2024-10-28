package com.sitas.gestionvuelos.resolvers;

import com.sitas.gestionvuelos.entities.Aeronave;
import com.sitas.gestionvuelos.entities.Vuelo;
import com.sitas.gestionvuelos.repositories.AeronaveRepository;
import com.sitas.gestionvuelos.repositories.VueloRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@CrossOrigin(origins="http://localhost:3000")
public class MutationResolver {

    private final AeronaveRepository aeronaveRepository;
    private final VueloRepository vueloRepository;

    public MutationResolver(AeronaveRepository aeronaveRepository, VueloRepository vueloRepository) {
        this.aeronaveRepository = aeronaveRepository;
        this.vueloRepository = vueloRepository;
    }

    @MutationMapping
    public Aeronave crearAeronave(@Argument String tipoAvion, @Argument int cantidadMaxAsientos, @Argument String distribucionAsientos) {
        Aeronave aeronave = new Aeronave(tipoAvion, cantidadMaxAsientos, distribucionAsientos);
        return aeronaveRepository.save(aeronave);
    }

    @MutationMapping
    public Aeronave actualizarAeronave(@Argument Long idAeronave, @Argument String tipoAvion,
                                       @Argument int cantidadMaxAsientos, @Argument String distribucionAsientos) {
        Aeronave aeronave = aeronaveRepository.findById(idAeronave)
                .orElseThrow(() -> new RuntimeException("Aeronave no encontrada"));
        aeronave.setTipoAvion(tipoAvion);
        aeronave.setCantidadMaxAsientos(cantidadMaxAsientos);
        aeronave.setDistribucionAsientos(distribucionAsientos);
        return aeronaveRepository.save(aeronave);
    }

    @MutationMapping
    public Boolean eliminarAeronave(@Argument Long idAeronave) {
        if (aeronaveRepository.existsById(idAeronave)) {
            aeronaveRepository.deleteById(idAeronave);
            return true;
        } else {
            return false;
        }
    }

    @MutationMapping
    public Vuelo crearVuelo(@Argument String numeroVuelo, @Argument String tipoVuelo, @Argument String ciudadOrigen,
                            @Argument String ciudadDestino, @Argument Long idAeronave, @Argument String fechaSalida,
                            @Argument String fechaLlegada, @Argument String horaSalida, @Argument String horaLlegada,
                            @Argument Float precio, @Argument Float porcentajeImpuestos, @Argument Float sobretasa) {
        Aeronave aeronave = aeronaveRepository.findById(idAeronave)
                .orElseThrow(() -> new RuntimeException("Aeronave no encontrada"));
        Vuelo vuelo = new Vuelo(numeroVuelo, tipoVuelo, ciudadOrigen, ciudadDestino, aeronave,
                LocalDate.parse(fechaSalida), LocalDate.parse(fechaLlegada),
                LocalTime.parse(horaSalida), LocalTime.parse(horaLlegada),
                BigDecimal.valueOf(precio), BigDecimal.valueOf(porcentajeImpuestos),
                BigDecimal.valueOf(sobretasa));
        return vueloRepository.save(vuelo);
    }

    @MutationMapping
    public Vuelo actualizarVuelo(@Argument String numeroVuelo, @Argument String tipoVuelo, @Argument String ciudadOrigen,
                                 @Argument String ciudadDestino, @Argument Long idAeronave, @Argument String fechaSalida,
                                 @Argument String fechaLlegada, @Argument String horaSalida, @Argument String horaLlegada,
                                 @Argument Float precio, @Argument Float porcentajeImpuestos, @Argument Float sobretasa) {
        Vuelo vuelo = vueloRepository.findById(numeroVuelo)
                .orElseThrow(() -> new RuntimeException("Vuelo no encontrado"));
        Aeronave aeronave = aeronaveRepository.findById(idAeronave)
                .orElseThrow(() -> new RuntimeException("Aeronave no encontrada"));
        vuelo.setTipoVuelo(tipoVuelo);
        vuelo.setCiudadOrigen(ciudadOrigen);
        vuelo.setCiudadDestino(ciudadDestino);
        vuelo.setAeronave(aeronave);
        vuelo.setFechaSalida(LocalDate.parse(fechaSalida));
        vuelo.setFechaLlegada(LocalDate.parse(fechaLlegada));
        vuelo.setHoraSalida(LocalTime.parse(horaSalida));
        vuelo.setHoraLlegada(LocalTime.parse(horaLlegada));
        vuelo.setPrecio(BigDecimal.valueOf(precio));
        vuelo.setPorcentajeImpuestos(BigDecimal.valueOf(porcentajeImpuestos));
        vuelo.setSobretasa(BigDecimal.valueOf(sobretasa));
        return vueloRepository.save(vuelo);
    }

    @MutationMapping
    public Boolean eliminarVuelo(@Argument String numeroVuelo) {
        if (vueloRepository.existsById(numeroVuelo)) {
            vueloRepository.deleteById(numeroVuelo);
            return true;
        } else {
            return false;
        }
    }

}


