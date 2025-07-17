package com.ventas_spa.ventas_spa.service;

import com.ventas_spa.ventas_spa.model.venta;
import com.ventas_spa.ventas_spa.repository.ventaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ventaService {
    @Autowired
    private ventaRepository ventaRepository;

    public List<venta> findAll(){
        return ventaRepository.findAll();
    }

    public venta findByID(long id){
        return ventaRepository.findById(id).get();
    }

    public venta save(venta venta){
        return ventaRepository.save(venta);
    }

    public void delete(Long id){
        ventaRepository.deleteById(id);  
    }
    /*
     * Nose si el metodo delete debiese estar, me refiero a no se cuanto 
     * nos interesa que podamos borrar el historial de venta de nuestro cliente
     * igual lo puse pero son libres de borrarlo si gustan
     */
    
}
