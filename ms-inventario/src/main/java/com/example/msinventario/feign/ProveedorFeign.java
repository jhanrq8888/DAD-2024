package com.example.msinventario.feign;

import com.example.msinventario.dto.ProveedorDto; // Importa el DTO de Proveedor
import org.springframework.cloud.openfeign.FeignClient; // Importa la anotación FeignClient para crear clientes HTTP
import org.springframework.http.ResponseEntity; // Importa la clase ResponseEntity para manejar respuestas HTTP
import org.springframework.web.bind.annotation.GetMapping; // Importa la anotación para manejar solicitudes GET
import org.springframework.web.bind.annotation.PathVariable; // Importa la anotación para manejar variables de ruta

// Define un cliente Feign para interactuar con el microservicio de proveedores
@FeignClient(name = "ms-Proveedor-service", path = "/proveedor")
public interface ProveedorFeign {

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorDto> listById(@PathVariable(required = true) Integer id);
}
