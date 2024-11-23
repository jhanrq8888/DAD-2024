package com.example.msproducto.entity;

import jakarta.persistence.Entity;            // Importa la anotación para definir una entidad JPA
import jakarta.persistence.GeneratedValue;   // Importa la anotación para la generación automática de IDs
import jakarta.persistence.GenerationType;   // Importa la enumeración para especificar la estrategia de generación de IDs
import jakarta.persistence.Id;               // Importa la anotación para definir la clave primaria
import lombok.Data;                        // Importa la anotación de Lombok para generar getters, setters, etc.

@Data                                      // Genera automáticamente getters, setters, toString, equals, y hashCode
@Entity                                    // Indica que esta clase es una entidad JPA que se mapeará a una tabla en la base de datos
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String modelo;
    private Integer codigo;

    private String imagenPath; // Nueva propiedad para la ruta de la imagen
}