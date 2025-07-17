package com.ventas_spa.ventas_spa.controlador;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ventas_spa.ventas_spa.assemblers.ventaModelAssembler;
import com.ventas_spa.ventas_spa.model.venta;
import com.ventas_spa.ventas_spa.service.ventaService;


@RestController
@RequestMapping("/api/v2/venta")

public class ventaControladorV2 {
    @Autowired
    private ventaService ventasService;
    
    @Autowired
    private ventaModelAssembler assembler;
    
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<venta>> getAllVentas() {
        List<EntityModel<venta>> ventas = ventasService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(ventas,
                linkTo(methodOn(ventaControladorV2.class).getAllVentas()).withSelfRel());
    }
    

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<venta>> createVenta(@RequestBody venta Venta) {
        venta newVenta = ventasService.save(Venta);
        return ResponseEntity
                .created(linkTo(methodOn(ventaControladorV2.class).getVentaByCodigo(newVenta.getId())).toUri())
                .body(assembler.toModel(newVenta));
    }


   @GetMapping(value = "/{codigo}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<venta> getVentaByCodigo(@PathVariable Long codigo) {
        venta Venta = ventasService.findByID(codigo);
        return assembler.toModel(Venta);
    }
    

    @PutMapping(value = "/{codigo}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<venta>> updateVenta(@PathVariable Long codigo, @RequestBody venta Venta) {
        Venta.setId(codigo);
        venta updatedVenta = ventasService.save(Venta);
        return ResponseEntity
                .ok(assembler.toModel(updatedVenta));
    }


    @DeleteMapping(value = "/{codigo}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteVenta(@PathVariable Long codigo) {
        ventasService.delete(codigo);
        return ResponseEntity.noContent().build();
    }
}