package com.example.msproducto.entity;

import jakarta.persistence.Entity;            // Importa la anotación para definir una entidad JPA
import jakarta.persistence.GeneratedValue;   // Importa la anotación para la generación automática de IDs
import jakarta.persistence.GenerationType;   // Importa la enumeración para especificar la estrategia de generación de IDs
import jakarta.persistence.Id;               // Importa la anotación para definir la clave primaria
import lombok.Data;                        // Importa la anotación de Lombok para generar getters, setters, etc.

@Data                                      // Genera automáticamente getters, setters, toString, equals, y hashCode
@Entity                                    // Indica que esta clase es una entidad JPA que se mapeará a una tabla en la base de datos
public class Producto {

    @Id                                     // Define este campo como la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Especifica que el valor del ID se generará automáticamente
    private Integer id;                     // ID único del producto

    private String nombre;                  // Nombre del producto

    private String modelo;                  // Modelo del producto

    private Integer codigo;                 // Código del producto (puede ser un SKU o similar)
}
