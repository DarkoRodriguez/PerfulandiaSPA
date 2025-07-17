package com.example.PerfulandiaInventario.service;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.PerfulandiaInventario.model.Inventario;
import com.example.PerfulandiaInventario.repository.InventarioRepository;
import com.example.PerfulandiaInventario.model.Producto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
@SpringBootTest
public class InventarioServiceTest {
    @Autowired
    private InventarioService inventarioService;

    // Crea un mock del repositorio de Carrera para simular su comportamiento.
    @MockBean
    private InventarioRepository inventarioRepository;
    Long id2 = 1L;
    private Producto producto = new Producto(id2, "SKU001","Perfume", "Perfume Deu Channel", "Hombre", 19000.0,30000.0);


    @Test
    public void testFindAll() {
        Long id = 1L;
        // Define el comportamiento del mock: cuando se llame a findAll(), devuelve una lista con una Carrera.
        when(inventarioRepository.findAll()).thenReturn(List.of(new Inventario(id, "Perfulandia Conce",producto, 192, 10)));

        // Llama al método findAll() del servicio.
        List<Inventario> inventarios = inventarioService.findAll();

        // Verifica que la lista devuelta no sea nula y contenga exactamente una Carrera.
        assertNotNull(inventarios);
        assertEquals(1, inventarios.size());
    }

    @Test
    public void testFindById() {
        Long id = 4L;
        Inventario inventario = new Inventario(id, "Perfulandia Conce",producto, 192, 10);

        // Define el comportamiento del mock: cuando se llame a findById() con "1", devuelve una Carrera opcional.
        when(inventarioRepository.findById(id)).thenReturn(Optional.of(inventario));

        // Llama al método findByCodigo() del servicio.
        Inventario found = inventarioService.findById(id);

        // Verifica que la Carrera devuelta no sea nula y que su código coincida con el código esperado.
        assertNotNull(found);
        assertEquals(id, found.getId());
    }

    @Test
    public void testSave() {
        Long id = 4L;
        Inventario inventario = new Inventario(id, "Perfulandia Conce",producto, 192, 10);

        // Define el comportamiento del mock: cuando se llame a save(), devuelve la Carrera proporcionada.
        when(inventarioRepository.save(inventario)).thenReturn(inventario);

        // Llama al método save() del servicio.
        Inventario saved = inventarioService.save(inventario);

        // Verifica que la Carrera guardada no sea nula y que su nombre coincida con el nombre esperado.
        assertNotNull(saved);
        assertEquals(id, saved.getId());
    }

    @Test
    public void testDeleteByCodigo() {
        Long codigo = 1L;

        // Define el comportamiento del mock: cuando se llame a deleteById(), no hace nada.
        doNothing().when(inventarioRepository).deleteById(codigo);

        // Llama al método deleteByCodigo() del servicio.
        inventarioService.delete(codigo);

        // Verifica que el método deleteById() del repositorio se haya llamado exactamente una vez con el código proporcionado.
        verify(inventarioRepository, times(1)).deleteById(codigo);
    }
}
