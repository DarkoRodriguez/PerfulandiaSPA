package com.example.perfulandia_envio.assemblers;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

import com.example.perfulandia_envio.controller.envioControllerV2;
import com.example.perfulandia_envio.model.envioModel;

@Component
public class envioModelAssambler implements RepresentationModelAssembler<envioModel, EntityModel<envioModel>> {

    @Override
    public EntityModel<envioModel> toModel(envioModel envio) {
        return EntityModel.of(envio,
                linkTo(methodOn(envioControllerV2.class).getEnvioByCodigo(envio.getId())).withSelfRel(),
                linkTo(methodOn(envioControllerV2.class).getAllEnvios()).withRel("envio"));
    }
}

