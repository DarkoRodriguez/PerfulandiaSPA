package com.example.PerfulandiaInventario.assemblers;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

import com.example.PerfulandiaInventario.controller.InventarioControllerV2;
import com.example.PerfulandiaInventario.model.Inventario;

@Component
public class InventarioModelAssembler implements RepresentationModelAssembler<Inventario, EntityModel<Inventario>> {

    @Override
    public EntityModel<Inventario> toModel(Inventario inv) {
        return EntityModel.of(inv,
                linkTo(methodOn(InventarioControllerV2.class).getInventarioByCodigo(inv.getId())).withSelfRel(),
                linkTo(methodOn(InventarioControllerV2.class).getAllInventarios()).withRel("Inventario"));
    }
}

