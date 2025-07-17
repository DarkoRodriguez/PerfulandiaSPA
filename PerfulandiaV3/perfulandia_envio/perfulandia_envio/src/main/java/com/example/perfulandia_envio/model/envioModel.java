package com.example.perfulandia_envio.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="envio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class envioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String direccion;

    @Column(unique = true, length = 10, nullable = false)
    private Integer idVenta;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = true)
    private Date fechaEnvio;

    @Column(nullable=false)
    private Date fechaEntrega;

}
