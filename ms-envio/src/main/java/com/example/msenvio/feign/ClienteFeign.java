package com.example.msenvio.feign;

import com.example.msenvio.dto.Cliente; // Importa el DTO Cliente
import org.springframework.cloud.openfeign.FeignClient; // Importa la anotación para Feign Client
import org.springframework.http.ResponseEntity; // Importa la clase ResponseEntity
import org.springframework.web.bind.annotation.GetMapping; // Importa la anotación para solicitudes GET
import org.springframework.web.bind.annotation.PathVariable; // Importa la anotación para variables de ruta

@FeignClient(name = "ms-Cliente-service", path = "/cliente") // Define el cliente Feign para comunicarse con el microservicio de cliente
public interface ClienteFeign {
    @GetMapping("/{id}") // Mapea la solicitud GET a "/cliente/{id}"
    public ResponseEntity<Cliente> getById(@PathVariable("id") Integer id); // Método para obtener un cliente por su ID
}
