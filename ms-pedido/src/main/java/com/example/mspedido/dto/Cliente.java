package com.example.mspedido.dto;

import lombok.Data;

@Data
public class Cliente {
    private Integer id;
    private String pombre;
    private String dni;
    private String direccion;
    private Integer telefono;
}
