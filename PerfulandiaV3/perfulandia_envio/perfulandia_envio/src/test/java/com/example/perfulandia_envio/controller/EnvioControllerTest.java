package com.example.perfulandia_envio.controller;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.example.perfulandia_envio.model.envioModel;
import com.example.perfulandia_envio.service.envioService;
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
@WebMvcTest(envioController.class)
public class EnvioControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private envioService enviosService;

    @Autowired
    private ObjectMapper objectMapper;

    private envioModel Envio;
    
    @BeforeEach
    void setUp() {
        // Inicializar el objeto usuarioModel con datos de prueba
        Envio = new envioModel();
        Envio.setId(1L);
        Envio.setDireccion("AVda Siempre viva");
        Envio.setIdVenta(1);
        Envio.setEstado("En camino");
        Envio.setFechaEntrega(Date.valueOf("2023-10-21")); // Asignar null o una fecha específica si es
        Envio.setFechaEnvio(Date.valueOf("2023-10-01"));

       /*  // Configurar el comportamiento del mock para los métodos del servicio
        when(usuarioService.findAll()).thenReturn(List.of(Usuario));
        when(usuarioService.findById(1L)).thenReturn(Usuario);
        when(usuarioService.Save(any(usuarioModel.class))).thenReturn(Usuario);*/
    }

    @Test
    public void testGetAllEnvios() throws Exception {
        when(enviosService.findAll()).thenReturn(List.of(Envio));

        mockMvc.perform(get("/api/v1/envios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].direccion").value("AVda Siempre viva"))
                .andExpect(jsonPath("$[0].idVenta").value(1))
                .andExpect(jsonPath("$[0].estado").value("En camino"))
                .andExpect(jsonPath("$[0].fechaEntrega").value("2023-10-21"))
                .andExpect(jsonPath("$[0].fechaEnvio").value("2023-10-01"));
    }

    @Test
    public void testGetEnvioById() throws Exception {
        when(enviosService.findById(1)).thenReturn(Envio);

        mockMvc.perform(get("/api/v1/envios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.direccion").value("AVda Siempre viva"))
                .andExpect(jsonPath("$.idVenta").value(1))
                .andExpect(jsonPath("$.estado").value("En camino"))
                .andExpect(jsonPath("$.fechaEntrega").value("2023-10-21"))
                .andExpect(jsonPath("$.fechaEnvio").value("2023-10-01"))   ;
    }

    @Test
    public void testCreateEnvio() throws Exception {
        when(enviosService.Save(any(envioModel.class))).thenReturn(Envio);

        mockMvc.perform(post("/api/v1/envios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Envio)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.direccion").value("AVda Siempre viva"))
                .andExpect(jsonPath("$.idVenta").value(1))
                .andExpect(jsonPath("$.estado").value("En camino"))
                .andExpect(jsonPath("$.fechaEntrega").value("2023-10-21"))
                .andExpect(jsonPath("$.fechaEnvio").value("2023-10-01"))   ;
    }

    @Test
    public void testUpdateEnvio() throws Exception {
        when(enviosService.findById(1)).thenReturn(Envio); 
        when(enviosService.Save(any(envioModel.class))).thenReturn(Envio);

        mockMvc.perform(put("/api/v1/envios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Envio)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.direccion").value("AVda Siempre viva"))
                .andExpect(jsonPath("$.idVenta").value(1))
                .andExpect(jsonPath("$.estado").value("En camino"))
                .andExpect(jsonPath("$.fechaEntrega").value("2023-10-21T00:00:00.000+00:00"))
                .andExpect(jsonPath("$.fechaEnvio").value("2023-10-01T00:00:00.000+00:00"))  ;
    }

    @Test
    public void testDeleteEnvio() throws Exception {
        doNothing().when(enviosService).delete(1L);

        mockMvc.perform(delete("/api/v1/envios/1"))
                .andExpect(status().isNoContent());
         
        verify(enviosService, times(1)).delete(1L);
    }

}
