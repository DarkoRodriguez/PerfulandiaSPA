package com.example.PerfulandiaInventario.service;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.PerfulandiaInventario.model.Producto;
import com.example.PerfulandiaInventario.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ProductoServiceTest {
    @Autowired
    private ProductoService productoService;

    // Crea un mock del repositorio de Carrera para simular su comportamiento.
    @MockBean
    private ProductoRepository productoRepository;

    @Test
    public void testFindAll() {
        Long id = 1L;
        // Define el comportamiento del mock: cuando se llame a findAll(), devuelve una lista con una Carrera.
        when(productoRepository.findAll()).thenReturn(List.of(new Producto(id, "SKU001","Perfume", "Perfume Deu Channel", "Hombre", 19000.0,30000.0)));

        // Llama al método findAll() del servicio.
        List<Producto> Productos = productoService.findAll();

        // Verifica que la lista devuelta no sea nula y contenga exactamente una Carrera.
        assertNotNull(Productos);
        assertEquals(1, Productos.size());
    }

    @Test
    public void testFindById() {
        Long id = 4L;
        Producto producto = new Producto(id, "SKU001","Perfume", "Perfume Deu Channel", "Hombre", 19000.0,30000.0);

        // Define el comportamiento del mock: cuando se llame a findById() con "1", devuelve una Carrera opcional.
        when(productoRepository.findById(id)).thenReturn(Optional.of(producto));

        // Llama al método findByCodigo() del servicio.
        Producto found = productoService.findById(id);

        // Verifica que la Carrera devuelta no sea nula y que su código coincida con el código esperado.
        assertNotNull(found);
        assertEquals(id, found.getId());
    }

    @Test
    public void testSave() {
        Long id = 4L;
        Producto producto = new Producto(id, "SKU001","Perfume", "Perfume Deu Channel", "Hombre", 19000.0,30000.0);

        // Define el comportamiento del mock: cuando se llame a save(), devuelve la Carrera proporcionada.
        when(productoRepository.save(producto)).thenReturn(producto);

        // Llama al método save() del servicio.
        Producto saved = productoService.save(producto);

        // Verifica que la Carrera guardada no sea nula y que su nombre coincida con el nombre esperado.
        assertNotNull(saved);
        assertEquals(id, saved.getId());
    }

    @Test
    public void testDeleteByCodigo() {
        Long codigo = 1L;

        // Define el comportamiento del mock: cuando se llame a deleteById(), no hace nada.
        doNothing().when(productoRepository).deleteById(codigo);

        // Llama al método deleteByCodigo() del servicio.
        productoService.delete(codigo);

        // Verifica que el método deleteById() del repositorio se haya llamado exactamente una vez con el código proporcionado.
        verify(productoRepository, times(1)).deleteById(codigo);
    }
}
