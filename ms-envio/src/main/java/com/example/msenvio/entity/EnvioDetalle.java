package com.example.msenvio.entity;

import com.example.msenvio.dto.Producto;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class EnvioDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer cantidad; // Cantidad de productos

    private Double precioUnitario; // Precio unitario del producto

    private String estado; // Estado del producto en el envío

    private String modelo; // Modelo del producto

    private Integer productoId; // ID del producto

    @Transient
    private Producto producto; // Objeto Producto, no persistido en la base de datos

    public EnvioDetalle() {
        this.precioUnitario = 0.0; // Inicializa el precio unitario a 0.0
        this.cantidad = 0; // Inicializa la cantidad a 0
    }

    // Getter para productoId
    public Integer getProductoId() {
        return productoId;
    }
}
