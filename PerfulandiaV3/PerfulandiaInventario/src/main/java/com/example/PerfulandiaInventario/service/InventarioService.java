package com.example.PerfulandiaInventario.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PerfulandiaInventario.model.Inventario;
import com.example.PerfulandiaInventario.repository.InventarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class InventarioService {
    @Autowired
    private InventarioRepository inventarioRepository;

    public List<Inventario> findAll(){
        return inventarioRepository.findAll();
    }

    public Inventario findById(long id){
        return inventarioRepository.findById(id).get();
    }

    public Inventario save(Inventario inv){
        return inventarioRepository.save(inv);
    }

    public void delete(Long id){
        inventarioRepository.deleteById(id);
    }

}
