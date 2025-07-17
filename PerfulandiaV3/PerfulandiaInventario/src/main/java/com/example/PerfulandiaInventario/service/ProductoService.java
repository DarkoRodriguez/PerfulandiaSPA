package com.example.PerfulandiaInventario.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PerfulandiaInventario.model.Producto;
import com.example.PerfulandiaInventario.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> findAll(){
        return productoRepository.findAll();
    }

    public Producto findById(long id){
        return productoRepository.findById(id).get();
    }


    public Producto save(Producto prod){
        return productoRepository.save(prod);
    }

    public void delete (Long id){
        productoRepository.deleteById(id);
    }

}
