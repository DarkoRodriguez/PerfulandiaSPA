package com.example.perfulandia_envio.controller;

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

import com.example.perfulandia_envio.model.envioModel;
import com.example.perfulandia_envio.service.envioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/envios")
@Tag(name = "Envios", description = "Operaciones relacionadas a los envios")
public class envioController {
    @Autowired
    private envioService envioService;
    
    @GetMapping
    @Operation(summary = "Obtiene los envios", description = "Obtiene una lista de todos los envios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = envioModel.class)))
    })
    public ResponseEntity<List<envioModel>> listar() {
        List<envioModel> envio = envioService.findAll();
        if (envio.isEmpty()){
            return ResponseEntity.noContent().build();        
        }
        return ResponseEntity.ok(envio);
    }
    

    @PostMapping
    @Operation(summary = "Ingresa nuevos envios", description = "Crea nuevos datos de envios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Envio creado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = envioModel.class)))
    })
    public ResponseEntity<envioModel> guardar(@RequestBody envioModel envio) {
        envioModel nuevoEnvio = envioService.Save(envio);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEnvio);
        
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene envios por ID", description = "Obtiene un envio especifico por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = envioModel.class))),
            @ApiResponse(responseCode = "404", description = "Envio no encontrado")
    })
    public ResponseEntity<envioModel> buscar(@PathVariable Long id) {
        try{
            envioModel envio = envioService.findById(id);
            return ResponseEntity.ok(envio);
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); 
        }
        
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualiza envios por ID", description = "Actualiza un envio especificado por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Envio actualizado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = envioModel.class))),
            @ApiResponse(responseCode = "404", description = "Envio no encontrado")
    })
    public ResponseEntity<envioModel> actualizar(@PathVariable Long id, @RequestBody envioModel envio) {
        try{
            envioModel env = envioService.findById(id);
            env.setId(id);
            env.setDireccion(envio.getDireccion());
            env.setIdVenta(envio.getIdVenta());
            env.setEstado(envio.getEstado());
            env.setFechaEnvio(envio.getFechaEnvio());
            env.setFechaEntrega(envio.getFechaEntrega());   

            envioService.Save(env);
            return ResponseEntity.ok(envio); 
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un envio por ID", description = "Elimina el envio especificado por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Envio eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Envio no encontrada")
    })
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try{
            envioService.delete(id);
            return ResponseEntity.noContent().build();
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}

