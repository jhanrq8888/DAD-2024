package com.example.msinventario.Entity;


import com.example.msinventario.dto.Producto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class AlmacenDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    private String categoria;

    private Double precio;

    private String estadoProducto;

    private String Talla;

    private Date FechaRecibido;

    private Date FechaVencimiento;

    private Integer produtoId;

    @Transient
    private Producto producto;

    public AlmacenDetalle(){
        this.precio = (double) 0;
    }

}
