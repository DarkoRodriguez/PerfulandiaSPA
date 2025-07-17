package com.example.perfulandia_usuarios.assemblers;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

import com.example.perfulandia_usuarios.controller.UsuarioControllerV2;
import com.example.perfulandia_usuarios.model.usuarioModel;

@Component
public class usuarioModelAssembler implements RepresentationModelAssembler<usuarioModel, EntityModel<usuarioModel>> {

    @Override
    public EntityModel<usuarioModel> toModel(usuarioModel usuario) {
        return EntityModel.of(usuario,
                linkTo(methodOn(UsuarioControllerV2.class).getUsuarioByCodigo(usuario.getId())).withSelfRel(),
                linkTo(methodOn(UsuarioControllerV2.class).getAllUsuarios()).withRel("usuario"));
    }
}