package com.example.perfulandia_usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.perfulandia_usuarios.model.usuarioModel;
@Repository
public interface UsuarioRepository extends JpaRepository<usuarioModel,Long> {
/* @Query("SELECT p FROM Paciente p WHERE p.apellidos =:apellido")
    List<Paciente> buscarPorApellidos(@Param("apellidos") String apellido);

    @Query("SELECT * FROM Paciente WHERE correo =:correo", nativeQuery =true)
    Paciente buscarPorCorreo(@Param("correo") String correo); */
}