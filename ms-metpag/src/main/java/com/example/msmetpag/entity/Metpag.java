package com.example.msmetpag.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Metpag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador único para el método de pago
    private String nombre; // Nombre del método de pago
    public Metpag() {}      // Constructor vacío (opcional, Lombok generará uno si es necesario)
    public Metpag(String nombre) {        // Constructor con parámetros (opcional, para facilidad al crear objetos)
        this.nombre = nombre;
    }
}
