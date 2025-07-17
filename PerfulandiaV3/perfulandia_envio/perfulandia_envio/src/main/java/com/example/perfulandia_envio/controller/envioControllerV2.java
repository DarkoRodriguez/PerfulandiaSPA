package com.example.perfulandia_envio.controller;
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

import com.example.perfulandia_envio.assemblers.envioModelAssambler;
import com.example.perfulandia_envio.model.envioModel;
import com.example.perfulandia_envio.service.envioService;



@RestController
@RequestMapping("/api/v2/envios")

public class envioControllerV2 {
    @Autowired
    private envioService envioService;
    
    @Autowired
    private envioModelAssambler assembler;
    
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<envioModel>> getAllEnvios() {
        List<EntityModel<envioModel>> envios = envioService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(envios,
                linkTo(methodOn(envioControllerV2.class).getAllEnvios()).withSelfRel());
    }
    

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<envioModel>> createEnvio(@RequestBody envioModel envio) {
        envioModel newEnvio = envioService.Save(envio);
        return ResponseEntity
                .created(linkTo(methodOn(envioControllerV2.class).getEnvioByCodigo(newEnvio.getId())).toUri())
                .body(assembler.toModel(newEnvio));
    }


   @GetMapping(value = "/{codigo}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<envioModel> getEnvioByCodigo(@PathVariable Long codigo) {
        envioModel envio = envioService.findById(codigo);
        return assembler.toModel(envio);
    }
    

    @PutMapping(value = "/{codigo}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<envioModel>> updateEnvio(@PathVariable Long codigo, @RequestBody envioModel envio) {
        envio.setId(codigo);
        envioModel updatedEnvio = envioService.Save(envio);
        return ResponseEntity
                .ok(assembler.toModel(updatedEnvio));
    }


    @DeleteMapping(value = "/{codigo}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteEnvio(@PathVariable Long codigo) {
        envioService.delete(codigo);
        return ResponseEntity.noContent().build();
    }
}