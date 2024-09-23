package com.example.msenvio.controller;

import com.example.msenvio.entity.Envio;
import com.example.msenvio.service.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Indica que esta clase es un controlador REST
@RequestMapping("/envio") // Mapea las solicitudes HTTP a esta clase
public class EnvioController {

    @Autowired
    private EnvioService envioService; // Inyección de dependencia del servicio de Envio

    @GetMapping // Maneja las solicitudes GET a "/envio"
    public ResponseEntity<List<Envio>> list() {
        List<Envio> envios = envioService.list(); // Llama al servicio para obtener la lista de envíos
        return ResponseEntity.ok(envios); // Retorna la lista en la respuesta con estado 200 OK
    }

    @PostMapping // Maneja las solicitudes POST a "/envio"
    public ResponseEntity<Envio> save(@RequestBody Envio envio) {
        Envio savedEnvio = envioService.save(envio); // Guarda el nuevo envío a través del servicio
        return ResponseEntity.ok(savedEnvio); // Retorna el envío guardado en la respuesta
    }

    @PutMapping // Maneja las solicitudes PUT a "/envio"
    public ResponseEntity<Envio> update(@RequestBody Envio envio) {
        Envio updatedEnvio = envioService.update(envio); // Actualiza el envío existente a través del servicio
        return ResponseEntity.ok(updatedEnvio); // Retorna el envío actualizado en la respuesta
    }

    @GetMapping("/{id}") // Maneja las solicitudes GET a "/envio/{id}"
    public ResponseEntity<Envio> listById(@PathVariable Integer id) {
        Optional<Envio> envio = envioService.findById(id); // Busca el envío por ID
        return envio.map(ResponseEntity::ok) // Si se encuentra, retorna el envío con estado 200 OK
                .orElseGet(() -> ResponseEntity.notFound().build()); // Si no se encuentra, retorna estado 404 Not Found
    }

    @DeleteMapping("/{id}") // Maneja las solicitudes DELETE a "/envio/{id}"
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        envioService.deleteById(id); // Elimina el envío por ID a través del servicio
        return ResponseEntity.ok("Eliminación Correcta"); // Retorna un mensaje de éxito
    }
}
