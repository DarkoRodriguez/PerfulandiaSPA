package com.example.PerfulandiaInventario.controller;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.example.PerfulandiaInventario.model.Inventario;
import com.example.PerfulandiaInventario.service.InventarioService;
import com.example.PerfulandiaInventario.model.Producto;
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
@WebMvcTest(InventarioController.class)
public class InventarioControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventarioService inventarioService;

    @Autowired
    private ObjectMapper objectMapper;

    private Inventario inv;
    
    private Producto prod = new Producto(1L, "SKU001", "Perfume", "Perfume Deu Channel", "Hombre", 19000.0, 30000.0);
    @BeforeEach
    void setUp() {
        // Inicializar el objeto usuarioModel con datos de prueba
        inv = new Inventario();
        inv.setId(1L);
        inv.setNombreSucursal("Sucursal Central");
        inv.setProducto(prod);
        inv.setStockDisponible(100);
        inv.setStockMinimo(10);

    }

    @Test
    public void testGetAllInventarios() throws Exception {
        when(inventarioService.findAll()).thenReturn(List.of(inv));

        mockMvc.perform(get("/api/v1/inventario"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombreSucursal").value("Sucursal Central"))
                .andExpect(jsonPath("$[0].producto").value(prod))
                .andExpect(jsonPath("$[0].stockDisponible").value(100))
                .andExpect(jsonPath("$[0].stockMinimo").value(10));
    }
 
    @Test
    public void testGetInventariosById() throws Exception {
        when(inventarioService.findById(1)).thenReturn(inv);

        mockMvc.perform(get("/api/v1/inventario/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombreSucursal").value("Sucursal Central"))
                .andExpect(jsonPath("$.producto").value(prod))
                .andExpect(jsonPath("$.stockDisponible").value(100))
                .andExpect(jsonPath("$.stockMinimo").value(10)) ;
    }

    @Test
    public void testCreateInventario() throws Exception {
        when(inventarioService.save(any(Inventario.class))).thenReturn(inv);

        mockMvc.perform(post("/api/v1/inventario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inv)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombreSucursal").value("Sucursal Central"))
                .andExpect(jsonPath("$.producto").value(prod))
                .andExpect(jsonPath("$.stockDisponible").value(100))
                .andExpect(jsonPath("$.stockMinimo").value(10)) ;
    }

    @Test
    public void testUpdateInventario() throws Exception {
        when(inventarioService.findById(1)).thenReturn(inv); 
        when(inventarioService.save(any(Inventario.class))).thenReturn(inv);

        mockMvc.perform(put("/api/v1/inventario/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inv)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombreSucursal").value("Sucursal Central"))
                .andExpect(jsonPath("$.producto").value(prod))
                .andExpect(jsonPath("$.stockDisponible").value(100))
                .andExpect(jsonPath("$.stockMinimo").value(10)) ;
    }

    @Test
    public void testDeleteInventario() throws Exception {
        doNothing().when(inventarioService).delete(1L);

        mockMvc.perform(delete("/api/v1/inventario/1"))
                .andExpect(status().isNoContent());
         
        verify(inventarioService, times(1)).delete(1L);
    }
}
