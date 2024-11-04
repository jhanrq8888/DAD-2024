package com.example.msfavorito.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

    @Data
    public class ProductoDto {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        private String nombre;
        private String modelo;
        private Integer codigo;
    }

