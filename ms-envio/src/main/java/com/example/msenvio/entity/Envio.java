package com.example.msenvio.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Envio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre_destinatario;
    private String nombre_empresa_envio;
    private String destino;
    private LocalDateTime fecha_de_envio;
    private LocalDateTime fecha_de_entrega;

}
