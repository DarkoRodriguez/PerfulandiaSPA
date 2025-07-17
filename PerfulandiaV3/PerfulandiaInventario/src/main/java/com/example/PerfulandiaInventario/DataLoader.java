package com.example.PerfulandiaInventario;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.example.PerfulandiaInventario.model.Inventario;
import com.example.PerfulandiaInventario.model.Producto;
import com.example.PerfulandiaInventario.repository.InventarioRepository;
import com.example.PerfulandiaInventario.repository.ProductoRepository;

import net.datafaker.Faker;
@Profile("test")
@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private InventarioRepository inventarioRepository;
    @Autowired
    private ProductoRepository productoRepository;
   
    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();
        
        // Generar productos
        for (int i = 0; i < 50; i++) {
            Producto producto = new Producto();
            producto.setCodigo(faker.code().isbn10());
            producto.setNombre(faker.commerce().productName());
            producto.setDescripcion(faker.lorem().sentence());
            producto.setCategoria(faker.commerce().department());
            producto.setPrecioCompra(Double.parseDouble(faker.commerce().price(1000, 1000000)));
            producto.setPrecioVenta(Double.parseDouble(faker.commerce().price(10.0, 10000.0)));
            productoRepository.save(producto);
        }
        
        List<Producto> productos = productoRepository.findAll();
        
        for (int i = 0; i < 50; i++) {
            Inventario inventario = new Inventario();
            inventario.setNombreSucursal(faker.address().cityName());
            inventario.setProducto(productos.get(random.nextInt(productos.size())));
            inventario.setStockDisponible(faker.number().numberBetween(1, 1000));
            inventario.setStockMinimo(faker.number().numberBetween(1, 10));
            inventarioRepository.save(inventario);
        }
        List<Inventario> inventarios = inventarioRepository.findAll();
    }
}
