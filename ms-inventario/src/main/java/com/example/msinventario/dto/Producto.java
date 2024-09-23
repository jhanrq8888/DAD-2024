package com.example.msinventario.dto;

import lombok.Data; // Importa la anotación de Lombok para generar automáticamente getters y setters

@Data // Genera automáticamente los métodos getters, setters, toString, equals y hashCode
public class Producto {
    private Integer id; // ID único del producto

    private String nombre; // Nombre del producto

    private String modelo; // Modelo del producto

    private Integer codigo; // Código del producto, puede ser un SKU o un código interno

    private Proveedor proveedor; // Relación con la entidad Proveedor, que representa al proveedor del producto
}
