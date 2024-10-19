package com.example.msinventario.feign;

import com.example.msinventario.dto.ProductoDto; // Importa el DTO de Producto
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker; // Importa la anotación CircuitBreaker
import org.springframework.cloud.openfeign.FeignClient; // Importa la anotación FeignClient para crear clientes HTTP
import org.springframework.http.ResponseEntity; // Importa la clase ResponseEntity para manejar respuestas HTTP
import org.springframework.web.bind.annotation.GetMapping; // Importa la anotación para manejar solicitudes GET
import org.springframework.web.bind.annotation.PathVariable; // Importa la anotación para manejar variables de ruta

// Define un cliente Feign para interactuar con el microservicio de catálogo
@FeignClient(name = "ms-producto-service", path = "/product")
public interface ProductoFeign {
    @GetMapping("/{id}")
    @CircuitBreaker(name = "productoByIdCB", fallbackMethod = "productoByIdFallback")
    public ResponseEntity<ProductoDto> getById(@PathVariable Integer id);

    // Método de fallback para manejar fallos en la llamada
    default ResponseEntity<ProductoDto> productoListById(Integer id, Exception e) {
        // Aquí puedes retornar un objeto ProductoDto vacío o un valor predeterminado
        return ResponseEntity.ok(new ProductoDto()); // Retorna un ProductoDto vacío en caso de error
    }
}
