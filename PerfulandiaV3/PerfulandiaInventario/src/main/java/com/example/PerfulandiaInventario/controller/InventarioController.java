package com.example.PerfulandiaInventario.controller;
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

import com.example.PerfulandiaInventario.model.Inventario;
import com.example.PerfulandiaInventario.service.InventarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/v1/inventario")
@Tag(name = "Inventario", description = "Operaciones relacionadas al inventario del sistema")
public class InventarioController {
    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    @Operation(summary = "Obtiene una lista del inventario", description = "Lista el inventario del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inventario.class)))
    })
    public ResponseEntity<List<Inventario>> listar(){
        List<Inventario> inventarios =inventarioService.findAll();
        if(inventarios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(inventarios);
    }
    
    @PostMapping
    @Operation(summary = "Guardar el inventario", description = "Crea un guardado del inventario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventario creado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inventario.class)))
    })
    public ResponseEntity<Inventario> guardad(@RequestBody Inventario inventario) {
        Inventario nuevoInventario = inventarioService.save(inventario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoInventario);
    }
    

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene inventarios por ID", description = "Obtiene los datos del inventario especificado por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inventario.class))),
            @ApiResponse(responseCode = "404", description = "Inventario no encontrado")
    })
    public ResponseEntity<Inventario> buscar(@PathVariable Long id) {
        try {
            Inventario inventario = inventarioService.findById(id);
            return ResponseEntity.ok(inventario);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{id}")
    @Operation(summary = "Actualiza el inventario por ID", description = "Actualiza los datos del inventario especificado por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventario actualizado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inventario.class))),
            @ApiResponse(responseCode = "404", description = "Inventario no encontrado")
    })
    public ResponseEntity<Inventario> actualizar(@PathVariable Long id ,@RequestBody Inventario inventario) {
        try {
            Inventario inv=inventarioService.findById(id);
            inv.setId(id);
            inv.setNombreSucursal(inventario.getNombreSucursal());
            inv.setProducto(inventario.getProducto());
            inv.setStockDisponible(inventario.getStockDisponible());
            inv.setStockMinimo(inventario.getStockMinimo());

            inventarioService.save(inv);
            return ResponseEntity.ok(inventario);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        } 
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un inventario por ID", description = "Elimina un inventario especificado por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventario eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Inventario no encontrado")
    })
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        try {
            inventarioService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
}
