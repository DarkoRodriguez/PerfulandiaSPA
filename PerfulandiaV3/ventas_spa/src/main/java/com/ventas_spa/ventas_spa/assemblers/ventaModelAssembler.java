package com.ventas_spa.ventas_spa.assemblers;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

import com.ventas_spa.ventas_spa.controlador.ventaControladorV2;
import com.ventas_spa.ventas_spa.model.venta;;

@Component
public class ventaModelAssembler implements RepresentationModelAssembler<venta, EntityModel<venta>> {

    @Override
    public EntityModel<venta> toModel(venta Venta) {
        return EntityModel.of(Venta,
                linkTo(methodOn(ventaControladorV2.class).getVentaByCodigo(Venta.getId())).withSelfRel(),
                linkTo(methodOn(ventaControladorV2.class).getAllVentas()).withRel("venta"));
    }
}

