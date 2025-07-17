package com.example.perfulandia_usuarios.controller;
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

import com.example.perfulandia_usuarios.assemblers.usuarioModelAssembler;
import com.example.perfulandia_usuarios.model.usuarioModel;
import com.example.perfulandia_usuarios.service.UsuarioService;



@RestController
@RequestMapping("/api/v2/usuarios")

public class UsuarioControllerV2 {
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private usuarioModelAssembler assembler;
    
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<usuarioModel>> getAllUsuarios() {
        List<EntityModel<usuarioModel>> envios = usuarioService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(envios,
                linkTo(methodOn(UsuarioControllerV2.class).getAllUsuarios()).withSelfRel());
    }
    

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<usuarioModel>> createEnvio(@RequestBody usuarioModel usuario) {
        usuarioModel newUsuario = usuarioService.Save(usuario);
        return ResponseEntity
                .created(linkTo(methodOn(UsuarioControllerV2.class).getUsuarioByCodigo(newUsuario.getId())).toUri())
                .body(assembler.toModel(newUsuario));
    }


   @GetMapping(value = "/{codigo}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<usuarioModel> getUsuarioByCodigo(@PathVariable Long codigo) {
        usuarioModel usuario = usuarioService.findById(codigo);
        return assembler.toModel(usuario);
    }
    

    @PutMapping(value = "/{codigo}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<usuarioModel>> updateUsuario(@PathVariable Long codigo, @RequestBody usuarioModel usuario) {
        usuario.setId(codigo);
        usuarioModel updatedUsuario = usuarioService.Save(usuario);
        return ResponseEntity
                .ok(assembler.toModel(updatedUsuario));
    }


    @DeleteMapping(value = "/{codigo}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteUsuario(@PathVariable Long codigo) {
        usuarioService.delete(codigo);
        return ResponseEntity.noContent().build();
    }
}
