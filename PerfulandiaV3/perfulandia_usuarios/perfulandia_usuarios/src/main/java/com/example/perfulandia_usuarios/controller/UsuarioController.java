package com.example.perfulandia_usuarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.perfulandia_usuarios.model.usuarioModel;
import com.example.perfulandia_usuarios.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/usuarios")
@Tag(name = "Usuarios", description = "Operaciones relacionadas a los usuarios del sistema")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping
    @Operation(summary = "Obtiene a los usuarios", description = "Obtiene una lista de todos los usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = usuarioModel.class)))
    })
    public ResponseEntity<List<usuarioModel>> listar() {
        List<usuarioModel> usuarios = usuarioService.findAll();
        if (usuarios.isEmpty()){
            return ResponseEntity.noContent().build();        
        }
        return ResponseEntity.ok(usuarios);
    }
    
    @PostMapping
    @Operation(summary = "Ingresa nuevos usuarios", description = "Crea e ingresa nuevos datos de usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario creado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = usuarioModel.class)))
    })
    public ResponseEntity<usuarioModel> guardar(@RequestBody usuarioModel usuario) {
        usuarioModel nuevoUsuario = usuarioService.Save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
        
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene usuarios por ID", description = "Obtiene los datos de un usuario especifico por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = usuarioModel.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<usuarioModel> buscar(@PathVariable Long id) {
        try{
            usuarioModel usuario = usuarioService.findById(id);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); 
        }
        
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualiza usuarios por ID", description = "Actualiza los datos de un usuario especificado por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = usuarioModel.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<usuarioModel> actualizar(@PathVariable Long id, @RequestBody usuarioModel usuario) {
        try{
            usuarioModel usr = usuarioService.findById(id);
            usr.setId(id);
            usr.setApellido(usuario.getApellido());
            usr.setNombre(usuario.getNombre());
            usr.setCorreo(usuario.getCorreo());
            usr.setRun(usuario.getRun());   
            usr.setFechaNacimiento(usuario.getFechaNacimiento());

            usuarioService.Save(usr);
            return ResponseEntity.ok(usuario); 
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un usuario por ID", description = "Elimina al usuario especificado por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try{
            usuarioService.delete(id);
            return ResponseEntity.noContent().build();
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
