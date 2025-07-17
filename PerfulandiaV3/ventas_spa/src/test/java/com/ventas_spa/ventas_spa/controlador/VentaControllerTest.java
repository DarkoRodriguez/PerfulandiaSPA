package com.ventas_spa.ventas_spa.controlador;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.ventas_spa.ventas_spa.model.venta;
import com.ventas_spa.ventas_spa.service.ventaService;

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

@WebMvcTest(ventaControlador.class)
public class VentaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ventaService VentasService;

    @Autowired
    private ObjectMapper objectMapper;

    private venta Venta;
    
    @BeforeEach
    void setUp() {
        // Inicializar el objeto usuarioModel con datos de prueba
        Venta = new venta();
        Venta.setId(1L);
        Venta.setCodigo("19992");
        Venta.setTotal(90000.0);
        Venta.setFecha(Date.valueOf("2023-10-01"));

        Venta.setPagoConfirmado(true);

    }

    @Test
    public void testGetAllVentas() throws Exception {
        when(VentasService.findAll()).thenReturn(List.of(Venta));

        mockMvc.perform(get("/api/v1/venta"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].codigo").value("19992"))
                .andExpect(jsonPath("$[0].total").value(90000.0))
                .andExpect(jsonPath("$[0].fecha").value("2023-10-01"))
                .andExpect(jsonPath("$[0].pagoConfirmado").value(true));
    }

    @Test
    public void testGetVentaById() throws Exception {
        when(VentasService.findByID(1)).thenReturn(Venta);

        mockMvc.perform(get("/api/v1/venta/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.codigo").value("19992"))
                .andExpect(jsonPath("$.total").value(90000.0))
                .andExpect(jsonPath("$.fecha").value("2023-10-01"))
                .andExpect(jsonPath("$.pagoConfirmado").value(true));
    }

    @Test
    public void testCreateVenta() throws Exception {
        when(VentasService.save(any(venta.class))).thenReturn(Venta);

        mockMvc.perform(post("/api/v1/venta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Venta)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.codigo").value("19992"))
                .andExpect(jsonPath("$.total").value(90000.0))
                .andExpect(jsonPath("$.fecha").value("2023-10-01"))
                .andExpect(jsonPath("$.pagoConfirmado").value(true));
    }

    @Test
    public void testUpdateVenta() throws Exception {
        when(VentasService.findByID(1)).thenReturn(Venta); 
        when(VentasService.save(any(venta.class))).thenReturn(Venta);

        mockMvc.perform(put("/api/v1/venta/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Venta)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.codigo").value("19992"))
                .andExpect(jsonPath("$.total").value(90000.0))
                .andExpect(jsonPath("$.fecha").value("2023-10-01T00:00:00.000+00:00"))
                .andExpect(jsonPath("$.pagoConfirmado").value(true));
    }

    @Test
    public void testDeleteVenta() throws Exception {
        doNothing().when(VentasService).delete(1L);

        mockMvc.perform(delete("/api/v1/venta/1"))
                .andExpect(status().isNoContent());
         
        verify(VentasService, times(1)).delete(1L);
    }
}
