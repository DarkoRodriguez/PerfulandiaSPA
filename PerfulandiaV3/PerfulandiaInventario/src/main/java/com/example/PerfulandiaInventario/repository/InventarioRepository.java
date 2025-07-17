package com.example.PerfulandiaInventario.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PerfulandiaInventario.model.Inventario;
@Repository
public interface InventarioRepository extends JpaRepository<Inventario,Long>{

   
}
