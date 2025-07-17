package com.ventas_spa.ventas_spa.service;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.ventas_spa.ventas_spa.model.venta;
import com.ventas_spa.ventas_spa.repository.ventaRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class VentaServiceTest {
    @Autowired
    private ventaService ventasService;

    // Crea un mock del repositorio de Carrera para simular su comportamiento.
    @MockBean
    private ventaRepository ventasRepository;

    @Test
    public void testFindAll() {
        Long id = 1L;
        // Define el comportamiento del mock: cuando se llame a findAll(), devuelve una lista con una Carrera.
        when(ventasRepository.findAll()).thenReturn(List.of(new venta(id, "19992",90000.0, Date.valueOf("2023-10-01"), true)));

        // Llama al método findAll() del servicio.
        List<venta> ventas = ventasService.findAll();

        // Verifica que la lista devuelta no sea nula y contenga exactamente una Carrera.
        assertNotNull(ventas);
        assertEquals(1, ventas.size());
    }

    @Test
    public void testFindById() {
        Long id = 4L;
        venta Venta = new venta(id, "19992",90000.0, Date.valueOf("2023-10-01"), true);

        // Define el comportamiento del mock: cuando se llame a findById() con "1", devuelve una Carrera opcional.
        when(ventasRepository.findById(id)).thenReturn(Optional.of(Venta));

        // Llama al método findByCodigo() del servicio.
        venta found = ventasService.findByID(id);

        // Verifica que la Carrera devuelta no sea nula y que su código coincida con el código esperado.
        assertNotNull(found);
        assertEquals(id, found.getId());
    }

    @Test
    public void testSave() {
        Long id = 4L;
        venta Venta = new venta(id, "19992",90000.0, Date.valueOf("2023-10-01"), true);

        // Define el comportamiento del mock: cuando se llame a save(), devuelve la Carrera proporcionada.
        when(ventasRepository.save(Venta)).thenReturn(Venta);

        // Llama al método save() del servicio.
        venta saved = ventasService.save(Venta);

        // Verifica que la Carrera guardada no sea nula y que su nombre coincida con el nombre esperado.
        assertNotNull(saved);
        assertEquals(id, saved.getId());
    }

    @Test
    public void testDeleteByCodigo() {
        Long codigo = 1L;

        // Define el comportamiento del mock: cuando se llame a deleteById(), no hace nada.
        doNothing().when(ventasRepository).deleteById(codigo);

        // Llama al método deleteByCodigo() del servicio.
        ventasService.delete(codigo);

        // Verifica que el método deleteById() del repositorio se haya llamado exactamente una vez con el código proporcionado.
        verify(ventasRepository, times(1)).deleteById(codigo);
    }
}
