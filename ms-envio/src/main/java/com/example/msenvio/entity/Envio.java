package com.example.msenvio.entity;

import com.example.msenvio.dto.Cliente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Envio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombreDestinatario; // Cambié a formato camelCase para consistencia
    private String nombreEmpresaEnvio; // Cambié a camelCase
    private String destino;
    private LocalDateTime fechaDeEnvio; // Cambié a camelCase
    private LocalDateTime fechaDeEntrega; // Cambié a camelCase
    private Integer clienteId; // ID de referencia al cliente

    // Relación con EnvioDetalle
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "envio_id") // Cambié a camelCase y lo relacioné con `envio_id` de EnvioDetalle
    private List<EnvioDetalle> envioDetalle; // Lista de detalles de envío

    // Cliente se trata como un dato transitorio que no se guarda en la BD
    @Transient
    private Cliente cliente; // Cambié a camelCase y corregí el nombre
}
