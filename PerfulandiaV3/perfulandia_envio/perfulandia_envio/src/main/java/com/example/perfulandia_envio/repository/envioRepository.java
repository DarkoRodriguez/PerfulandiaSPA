package com.example.perfulandia_envio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.perfulandia_envio.model.envioModel;
@Repository
public interface envioRepository extends JpaRepository<envioModel,Long> {
/* @Query("SELECT p FROM Paciente p WHERE p.apellidos =:apellido")
    List<Paciente> buscarPorApellidos(@Param("apellidos") String apellido);

    @Query("SELECT * FROM Paciente WHERE correo =:correo", nativeQuery =true)
    Paciente buscarPorCorreo(@Param("correo") String correo); */
}

