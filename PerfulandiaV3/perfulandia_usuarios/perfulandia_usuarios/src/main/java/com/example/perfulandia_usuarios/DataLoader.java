package com.example.perfulandia_usuarios;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.example.perfulandia_usuarios.model.usuarioModel;
import com.example.perfulandia_usuarios.repository.UsuarioRepository;

import net.datafaker.Faker;
@Profile("test")
@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private UsuarioRepository usuarioRepository;
   
    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();
        
        // Generar usuarios
        for (int i = 0; i < 50; i++) {
            usuarioModel usuario = new usuarioModel();
            usuario.setRun(faker.idNumber().valid());
            usuario.setNombre(faker.name().firstName());
            usuario.setApellido(faker.name().lastName());
            usuario.setFechaNacimiento(new Date());
            usuario.setCorreo(faker.internet().emailAddress());
            usuarioRepository.save(usuario);
        }
        
        List<usuarioModel> usuarios = usuarioRepository.findAll();
        
    }
}