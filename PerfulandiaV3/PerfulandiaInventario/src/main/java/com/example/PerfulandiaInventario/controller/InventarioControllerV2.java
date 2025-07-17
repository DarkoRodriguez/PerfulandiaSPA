package com.example.PerfulandiaInventario.controller;
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

import com.example.PerfulandiaInventario.assemblers.InventarioModelAssembler;
import com.example.PerfulandiaInventario.model.Inventario;
import com.example.PerfulandiaInventario.service.InventarioService;



@RestController
@RequestMapping("/api/v2/inventario")

public class InventarioControllerV2 {
    @Autowired
    private InventarioService inventarioService;
    
    @Autowired
    private InventarioModelAssembler assembler;
    
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Inventario>> getAllInventarios() {
        List<EntityModel<Inventario>> inventarios = inventarioService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(inventarios,
                linkTo(methodOn(InventarioControllerV2.class).getAllInventarios()).withSelfRel());
    }
    

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Inventario>> createInventario(@RequestBody Inventario inv) {
        Inventario newInventario = inventarioService.save(inv);
        return ResponseEntity
                .created(linkTo(methodOn(InventarioControllerV2.class).getInventarioByCodigo(newInventario.getId())).toUri())
                .body(assembler.toModel(newInventario));
    }


   @GetMapping(value = "/{codigo}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Inventario> getInventarioByCodigo(@PathVariable Long codigo) {
        Inventario inv = inventarioService.findById(codigo);
        return assembler.toModel(inv);
    }
    

    @PutMapping(value = "/{codigo}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Inventario>> updateInventario(@PathVariable Long codigo, @RequestBody Inventario inv) {
        inv.setId(codigo);
        Inventario updatedInventario = inventarioService.save(inv);
        return ResponseEntity
                .ok(assembler.toModel(updatedInventario));
    }


    @DeleteMapping(value = "/{codigo}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteinventario(@PathVariable Long codigo) {
        inventarioService.delete(codigo);
        return ResponseEntity.noContent().build();
    }
}

