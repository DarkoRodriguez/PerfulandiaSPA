package com.ventas_spa.ventas_spa;


import com.ventas_spa.ventas_spa.model.*;
import com.ventas_spa.ventas_spa.repository.*;
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
    private ventaRepository VentaRepository;
   
    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();
        
        // Generar ventas
        for (int i = 0; i < 50; i++) {
            venta Venta = new venta();
            Venta.setCodigo(faker.code().isbn10());
            Venta.setTotal(Double.parseDouble(faker.commerce().price(1000, 1000000)));
            Venta.setFecha(new Date());
            Venta.setPagoConfirmado(faker.bool().bool());
        
            VentaRepository.save(Venta);
        }
        
        List<venta> ventas = VentaRepository.findAll();
        
    }
}