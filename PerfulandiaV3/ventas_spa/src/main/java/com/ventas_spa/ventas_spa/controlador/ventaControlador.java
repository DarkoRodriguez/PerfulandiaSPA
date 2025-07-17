package com.ventas_spa.ventas_spa.controlador;

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

import com.ventas_spa.ventas_spa.model.venta;
import com.ventas_spa.ventas_spa.service.ventaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/venta")
@Tag(name = "Ventas", description = "Operaciones relacionadas a las ventas de Perfulandia")
public class ventaControlador {
    
    @Autowired
    private ventaService ventaService;

    @GetMapping
    @Operation(summary = "Obtiene una lista de ventas", description = "Obtiene un listado de las ventas del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = venta.class)))
    })
    public ResponseEntity<List<venta>> listar(){
        List<venta> ventas = ventaService.findAll();
        if (ventas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ventas);
    }

    @PostMapping
    @Operation(summary = "Registra las ventas", description = "Ingresa nuevas ventas al sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venta creada exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = venta.class)))
    })
    public ResponseEntity<venta> guardar(@RequestBody venta venta){
        venta nuevaVenta = ventaService.save(venta);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaVenta);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene ventas por ID", description = "Obtiene los datos de una venta especifica por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = venta.class))),
            @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    })
    public ResponseEntity<venta> buscar(@PathVariable Long id){
        try{
            venta venta = ventaService.findByID(id);
            return ResponseEntity.ok(venta);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza ventas por ID", description = "Actualiza los datos de una venta especifica por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venta actualizada exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = venta.class))),
            @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    })
    public ResponseEntity<venta> actualizar(@PathVariable Long id, @RequestBody venta venta){
        try{
            venta vent=ventaService.findByID(id);
            vent.setId(id);
            vent.setCodigo(venta.getCodigo());
            vent.setTotal(venta.getTotal());
            vent.setFecha(venta.getFecha());
            vent.setPagoConfirmado(venta.getPagoConfirmado());

            ventaService.save(vent);
            return ResponseEntity.ok(venta);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina una venta por ID", description = "Elimina una venta especifica por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venta eliminada exitosamento"),
            @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    })
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        try{
            ventaService.delete(id);
            return ResponseEntity.noContent().build();
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
