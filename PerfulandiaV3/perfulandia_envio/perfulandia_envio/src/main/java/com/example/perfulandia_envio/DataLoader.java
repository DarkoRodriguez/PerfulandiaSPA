package com.example.perfulandia_envio;


import com.example.perfulandia_envio.model.*;
import com.example.perfulandia_envio.repository.*;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Random;
//@Profile("test")
@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private envioRepository EnvioRepository;
   
    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();
        
        // Generar envios
        for (int i = 0; i < 50; i++) {
            envioModel envio = new envioModel();
            envio.setDireccion(faker.address().streetAddress());
            envio.setEstado(faker.options().option("En Camino", "Entregado"));
            envio.setFechaEntrega(new Date());
            envio.setFechaEnvio(new Date());
            envio.setIdVenta(i+1);
            EnvioRepository.save(envio);
        }
        
        List<envioModel> envios = EnvioRepository.findAll();
        
    }
}