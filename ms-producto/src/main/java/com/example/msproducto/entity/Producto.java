package com.example.msproducto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String modelo;
    private Integer codigo;

    // Agregar campo para almacenar la imagen
    private byte[] imagen;  // Almacenar imagen como un array de bytes
}
