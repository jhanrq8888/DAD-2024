package com.example.msenvio.feign;

import com.example.msenvio.dto.Producto; // Importa el DTO Producto
import org.springframework.cloud.openfeign.FeignClient; // Importa la anotación para Feign Client
import org.springframework.http.ResponseEntity; // Importa la clase ResponseEntity
import org.springframework.web.bind.annotation.GetMapping; // Importa la anotación para solicitudes GET
import org.springframework.web.bind.annotation.PathVariable; // Importa la anotación para variables de ruta

@FeignClient(name = "ms-Producto-service", path = "/producto") // Define el cliente Feign para comunicarse con el microservicio de producto
public interface ProductoFeign {
    @GetMapping("/{id}") // Mapea la solicitud GET a "/producto/{id}"
    public ResponseEntity<Producto> getById(@PathVariable("id") Integer id); // Método para obtener un producto por su ID
}
