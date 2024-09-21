package com.example.msinventario.Entity;

import com.example.msinventario.dto.Producto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class InventarioDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String categoria;

    private Double precio;

    private String estadoProducto;

    private String modelo;

    private Integer codigo;

    private Date fechaRecibido;

    private Integer productoId;

    @Transient
    private Producto producto;

    public InventarioDetalle() {
        this.precio = (double) 0;
    }
}