package com.example.PerfulandiaInventario.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.example.PerfulandiaInventario.model.Producto;
import com.example.PerfulandiaInventario.service.ProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.List;

@WebMvcTest(ProductoController.class)
public class ProductoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Producto producto;
    
    @BeforeEach
    void setUp() {
        // Inicializar el objeto usuarioModel con datos de prueba
        producto = new Producto();
        producto.setId(1L);
        producto.setCodigo("SKU500");;
        producto.setNombre("Perfume");;
        producto.setDescripcion("Perfume deau Channel");
        producto.setCategoria("Hombres");; // Asignar null o una fecha específica si es
        producto.setPrecioCompra(19000.0);
        producto.setPrecioVenta(25000.0);


    }

    @Test
    public void testGetAllProductos() throws Exception {
        when(productoService.findAll()).thenReturn(List.of(producto));

        mockMvc.perform(get("/api/v1/inventario/producto"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].codigo").value("SKU500"))
                .andExpect(jsonPath("$[0].nombre").value("Perfume"))
                .andExpect(jsonPath("$[0].descripcion").value("Perfume deau Channel"))
                .andExpect(jsonPath("$[0].categoria").value("Hombres"))
                .andExpect(jsonPath("$[0].precioCompra").value(19000.0))
                .andExpect(jsonPath("$[0].precioVenta").value(25000.0));
    }

    @Test
    public void testGetProductoById() throws Exception {
        when(productoService.findById(1)).thenReturn(producto);

        mockMvc.perform(get("/api/v1/inventario/producto/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.codigo").value("SKU500"))
                .andExpect(jsonPath("$.nombre").value("Perfume"))
                .andExpect(jsonPath("$.descripcion").value("Perfume deau Channel"))
                .andExpect(jsonPath("$.categoria").value("Hombres"))
                .andExpect(jsonPath("$.precioCompra").value(19000.0)) 
                .andExpect(jsonPath("$.precioVenta").value(25000.0));
    }

    @Test
    public void testCreateProducto() throws Exception {
        when(productoService.save(any(Producto.class))).thenReturn(producto);

        mockMvc.perform(post("/api/v1/inventario/producto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.codigo").value("SKU500"))
                .andExpect(jsonPath("$.nombre").value("Perfume"))
                .andExpect(jsonPath("$.descripcion").value("Perfume deau Channel"))
                .andExpect(jsonPath("$.categoria").value("Hombres"))
                .andExpect(jsonPath("$.precioCompra").value(19000.0)) 
                .andExpect(jsonPath("$.precioVenta").value(25000.0));
    }

    @Test
    public void testUpdateProducto() throws Exception {
        when(productoService.findById(1)).thenReturn(producto); 
        when(productoService.save(any(Producto.class))).thenReturn(producto);

        mockMvc.perform(put("/api/v1/inventario/producto/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.codigo").value("SKU500"))
                .andExpect(jsonPath("$.nombre").value("Perfume"))
                .andExpect(jsonPath("$.descripcion").value("Perfume deau Channel"))
                .andExpect(jsonPath("$.categoria").value("Hombres"))
                .andExpect(jsonPath("$.precioCompra").value(19000.0)) 
                .andExpect(jsonPath("$.precioVenta").value(25000.0));
    }

    @Test
    public void testDeleteProducto() throws Exception {
        doNothing().when(productoService).delete(1L);

        mockMvc.perform(delete("/api/v1/inventario/producto/1"))
                .andExpect(status().isNoContent());
         
        verify(productoService, times(1)).delete(1L);
    }
}