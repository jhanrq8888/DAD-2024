package com.example.msinventario.dto;

import lombok.Data;

@Data
public class Producto {
    private Integer id;

    private String nombre;

    private String modelo;

    private Integer codigo;

    private Proveedor proveedor;
}
