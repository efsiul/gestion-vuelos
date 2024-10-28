package com.sitas.gestionvuelos.resolvers;

import com.sitas.gestionvuelos.entities.Aeronave;
import com.sitas.gestionvuelos.repositories.AeronaveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class MutationResolverTest {

    @Mock
    private AeronaveRepository aeronaveRepository;

    @InjectMocks
    private MutationResolver mutationResolver;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearAeronave() {
        // Datos de prueba
        Aeronave aeronave = new Aeronave("Boeing 747", 400, "3-4-3");

        // Simulación del comportamiento del repositorio
        when(aeronaveRepository.save(any(Aeronave.class))).thenReturn(aeronave);

        // Invocar el método y verificar el resultado
        Aeronave resultado = mutationResolver.crearAeronave("Boeing 747", 400, "3-4-3");

        assertNotNull(resultado);
        assertEquals("Boeing 747", resultado.getTipoAvion());

        // Verificar que el repositorio fue invocado correctamente
        verify(aeronaveRepository, times(1)).save(any(Aeronave.class));
    }
}
