package com.example.perfulandia_envio.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.perfulandia_envio.model.envioModel;
import com.example.perfulandia_envio.repository.envioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class EnvioServiceTest {

    @Autowired
    private envioService enviosService;

    // Crea un mock del repositorio de Carrera para simular su comportamiento.
    @MockBean
    private envioRepository enviosRepository;

    @Test
    public void testFindAll() {
        Long id = 1L;
        // Define el comportamiento del mock: cuando se llame a findAll(), devuelve una lista con una Carrera.
        when(enviosRepository.findAll()).thenReturn(List.of(new envioModel(id, "AVda Siempre viva",1, "En camino", null, Date.valueOf("2023-10-01"))));

        // Llama al método findAll() del servicio.
        List<envioModel> envios = enviosService.findAll();

        // Verifica que la lista devuelta no sea nula y contenga exactamente una Carrera.
        assertNotNull(envios);
        assertEquals(1, envios.size());
    }

    @Test
    public void testFindById() {
        Long id = 4L;
        envioModel envio = new envioModel(id,  "AVda Siempre viva",1, "En camino", null, Date.valueOf("2023-10-01"));

        // Define el comportamiento del mock: cuando se llame a findById() con "1", devuelve una Carrera opcional.
        when(enviosRepository.findById(id)).thenReturn(Optional.of(envio));

        // Llama al método findByCodigo() del servicio.
        envioModel found = enviosService.findById(id);

        // Verifica que la Carrera devuelta no sea nula y que su código coincida con el código esperado.
        assertNotNull(found);
        assertEquals(id, found.getId());
    }

    @Test
    public void testSave() {
        Long id = 4L;
        envioModel envio = new envioModel(id,  "AVda Siempre viva",1, "En camino", null, Date.valueOf("2023-10-01"));

        // Define el comportamiento del mock: cuando se llame a save(), devuelve la Carrera proporcionada.
        when(enviosRepository.save(envio)).thenReturn(envio);

        // Llama al método save() del servicio.
        envioModel saved = enviosService.Save(envio);

        // Verifica que la Carrera guardada no sea nula y que su nombre coincida con el nombre esperado.
        assertNotNull(saved);
        assertEquals(id, saved.getId());
    }

    @Test
    public void testDeleteByCodigo() {
        Long codigo = 1L;

        // Define el comportamiento del mock: cuando se llame a deleteById(), no hace nada.
        doNothing().when(enviosRepository).deleteById(codigo);

        // Llama al método deleteByCodigo() del servicio.
        enviosService.delete(codigo);

        // Verifica que el método deleteById() del repositorio se haya llamado exactamente una vez con el código proporcionado.
        verify(enviosRepository, times(1)).deleteById(codigo);
    }
}
