package com.sitas.gestionvuelos.resolvers;

import com.sitas.gestionvuelos.entities.Aeronave;
import com.sitas.gestionvuelos.entities.Vuelo;
import com.sitas.gestionvuelos.repositories.AeronaveRepository;
import com.sitas.gestionvuelos.repositories.VueloRepository;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class QueryResolver {

    private final AeronaveRepository aeronaveRepository;
    private final VueloRepository vueloRepository;

    public QueryResolver(AeronaveRepository aeronaveRepository, VueloRepository vueloRepository) {
        this.aeronaveRepository = aeronaveRepository;
        this.vueloRepository = vueloRepository;
    }

    @QueryMapping
    public List<Aeronave> obtenerAeronaves() {
        return aeronaveRepository.findAll();
    }

    @QueryMapping
    public List<Vuelo> obtenerVuelos() {
        return vueloRepository.findAll();
    }
}
