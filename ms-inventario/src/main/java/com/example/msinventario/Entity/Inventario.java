package com.example.msinventario.Entity;

import com.example.msinventario.dto.Proveedor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;


import java.util.List;

@Entity
@Data
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    private String descripcion;

    private Double stock;

    private String modelo;

    private Integer codigo;

    private Integer proveedorId;

    public Inventario() {
        this.stock = (double) 0;
    }

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "inventario_id")
    private List<InventarioDetalle> inventarioDetalle;

    @Transient
    private Proveedor proveedor;
}