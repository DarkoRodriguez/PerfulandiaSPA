package com.example.perfulandia_envio.service;

import com.example.perfulandia_envio.model.envioModel;
import com.example.perfulandia_envio.repository.envioRepository;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class envioService {
    @Autowired
    private envioRepository envioRepository;

    public List<envioModel> findAll(){
        return envioRepository.findAll();
    }

    public envioModel findById(long id){
        return envioRepository.findById(id).get();
    }

    public envioModel Save(envioModel envio){
        return envioRepository.save(envio);
    } // La funcion save sirve tanto para crear como actualizar

    public void delete (Long id){
        envioRepository.deleteById(id);
    }
}
