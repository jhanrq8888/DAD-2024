package com.example.msinventario.feign;

import com.example.msinventario.dto.ProveedorDto; // Importa el DTO de Proveedor
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;// Importa la anotación CircuitBreaker
import org.springframework.cloud.openfeign.FeignClient; // Importa la anotación FeignClient para crear clientes HTTP
import org.springframework.http.ResponseEntity; // Importa la clase ResponseEntity para manejar respuestas HTTP
import org.springframework.web.bind.annotation.GetMapping; // Importa la anotación para manejar solicitudes GET
import org.springframework.web.bind.annotation.PathVariable; // Importa la anotación para manejar variables de ruta

// Define un cliente Feign para interactuar con el microservicio de proveedores
@FeignClient(name = "ms-proveedor-service", path = "/proveedor")
public interface ProveedorFeign {

    // Método para obtener un proveedor por su ID
    @GetMapping("/{id}")
    @CircuitBreaker(name = "proveedorListByIdCB", fallbackMethod = "proveedorListById")
    public ResponseEntity<ProveedorDto> getById(@PathVariable Integer id);

    // Método fallback que se ejecuta si la llamada al servicio de proveedores falla
    default ResponseEntity<ProveedorDto> proveedorListById(Integer id, Exception e) {
        // Retorna un ProveedorDto vacío o con valores predeterminados

        return ResponseEntity.ok(new ProveedorDto());
    }
}
