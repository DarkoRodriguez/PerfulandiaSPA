package com.example.perfulandia_usuarios.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.example.perfulandia_usuarios.model.usuarioModel;
import com.example.perfulandia_usuarios.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    private usuarioModel Usuario;
    
    @BeforeEach
    void setUp() {
        // Inicializar el objeto usuarioModel con datos de prueba
        Usuario = new usuarioModel();
        Usuario.setId(1L);
        Usuario.setRun("11999222-3");
        Usuario.setNombre("Juan");
        Usuario.setApellido("Pérez");
        Usuario.setFechaNacimiento(null); // Asignar null o una fecha específica si es necesario
        Usuario.setCorreo("juan@gmail.com");

       /*  // Configurar el comportamiento del mock para los métodos del servicio
        when(usuarioService.findAll()).thenReturn(List.of(Usuario));
        when(usuarioService.findById(1L)).thenReturn(Usuario);
        when(usuarioService.Save(any(usuarioModel.class))).thenReturn(Usuario);*/
    }

    @Test
    public void testGetAllUsuarios() throws Exception {
        when(usuarioService.findAll()).thenReturn(List.of(Usuario));

        mockMvc.perform(get("/api/v1/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Juan"))
                .andExpect(jsonPath("$[0].apellido").value("Pérez"))
                .andExpect(jsonPath("$[0].run").value("11999222-3"))
                .andExpect(jsonPath("$[0].fechaNacimiento").doesNotExist())
                .andExpect(jsonPath("$[0].correo").value("juan@gmail.com"));
    }

    @Test
    public void testGetUsuarioById() throws Exception {
        when(usuarioService.findById(1)).thenReturn(Usuario);

        mockMvc.perform(get("/api/v1/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.apellido").value("Pérez"))
                .andExpect(jsonPath("$.run").value("11999222-3"))
                .andExpect(jsonPath("$.fechaNacimiento").doesNotExist())
                .andExpect(jsonPath("$.correo").value("juan@gmail.com"))   ;
    }

    @Test
    public void testCreateUsuario() throws Exception {
        when(usuarioService.Save(any(usuarioModel.class))).thenReturn(Usuario);

        mockMvc.perform(post("/api/v1/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Usuario)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.apellido").value("Pérez"))
                .andExpect(jsonPath("$.run").value("11999222-3"))
                .andExpect(jsonPath("$.fechaNacimiento").doesNotExist())
                .andExpect(jsonPath("$.correo").value("juan@gmail.com"));
    }

    @Test
    public void testUpdateUsuario() throws Exception {
        when(usuarioService.findById(1)).thenReturn(Usuario); 
        when(usuarioService.Save(any(usuarioModel.class))).thenReturn(Usuario);

        mockMvc.perform(put("/api/v1/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Usuario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.apellido").value("Pérez"))
                .andExpect(jsonPath("$.run").value("11999222-3"))
                .andExpect(jsonPath("$.fechaNacimiento").doesNotExist())
                .andExpect(jsonPath("$.correo").value("juan@gmail.com"));
    }

    @Test
    public void testDeleteUsuario() throws Exception {
        doNothing().when(usuarioService).delete(1L);

        mockMvc.perform(delete("/api/v1/usuarios/1"))
                .andExpect(status().isNoContent());

        verify(usuarioService, times(1)).delete(1L);
    }

}
