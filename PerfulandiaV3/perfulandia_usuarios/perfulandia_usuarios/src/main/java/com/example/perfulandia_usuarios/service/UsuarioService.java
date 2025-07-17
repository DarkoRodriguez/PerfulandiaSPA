package com.example.perfulandia_usuarios.service;

import com.example.perfulandia_usuarios.model.usuarioModel;
import com.example.perfulandia_usuarios.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<usuarioModel> findAll(){
        return usuarioRepository.findAll();
    }

    public usuarioModel findById(long id){
        return usuarioRepository.findById(id).get();
    }

    public usuarioModel Save(usuarioModel usuario){
        return usuarioRepository.save(usuario);
    } // La funcion save sirve tanto para crear como actualizar

    public void delete (Long id){
        usuarioRepository.deleteById(id);
    }
}

