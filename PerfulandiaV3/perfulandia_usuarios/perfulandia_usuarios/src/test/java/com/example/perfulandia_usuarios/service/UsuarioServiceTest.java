package com.example.perfulandia_usuarios.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.perfulandia_usuarios.model.usuarioModel;
import com.example.perfulandia_usuarios.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UsuarioServiceTest {
    @Autowired
    private UsuarioService usuarioService;

    // Crea un mock del repositorio de Carrera para simular su comportamiento.
    @MockBean
    private UsuarioRepository usuarioRepository;

    @Test
    public void testFindAll() {
        Long id = 1L;
        // Define el comportamiento del mock: cuando se llame a findAll(), devuelve una lista con una Carrera.
        when(usuarioRepository.findAll()).thenReturn(List.of(new usuarioModel(id, "11999222-3","Juan", "Pérez", null, "juan@mail.com")));

        // Llama al método findAll() del servicio.
        List<usuarioModel> usuarios = usuarioService.findAll();

        // Verifica que la lista devuelta no sea nula y contenga exactamente una Carrera.
        assertNotNull(usuarios);
        assertEquals(1, usuarios.size());
    }

    @Test
    public void testFindById() {
        Long id = 4L;
        usuarioModel usuario = new usuarioModel(id, "11999222-3","Juan", "Pérez", null, "juan@mail.com");

        // Define el comportamiento del mock: cuando se llame a findById() con "1", devuelve una Carrera opcional.
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        // Llama al método findByCodigo() del servicio.
        usuarioModel found = usuarioService.findById(id);

        // Verifica que la Carrera devuelta no sea nula y que su código coincida con el código esperado.
        assertNotNull(found);
        assertEquals(id, found.getId());
    }

    @Test
    public void testSave() {
        Long id = 4L;
        usuarioModel usuario = new usuarioModel(id , "11999222-3","Juan", "Pérez", null, "juan@mail.com");

        // Define el comportamiento del mock: cuando se llame a save(), devuelve la Carrera proporcionada.
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        // Llama al método save() del servicio.
        usuarioModel saved = usuarioService.Save(usuario);

        // Verifica que la Carrera guardada no sea nula y que su nombre coincida con el nombre esperado.
        assertNotNull(saved);
        assertEquals("Juan", saved.getNombre());
    }

    @Test
    public void testDeleteByCodigo() {
        Long codigo = 1L;

        // Define el comportamiento del mock: cuando se llame a deleteById(), no hace nada.
        doNothing().when(usuarioRepository).deleteById(codigo);

        // Llama al método deleteByCodigo() del servicio.
        usuarioService.delete(codigo);

        // Verifica que el método deleteById() del repositorio se haya llamado exactamente una vez con el código proporcionado.
        verify(usuarioRepository, times(1)).deleteById(codigo);
    }
}
