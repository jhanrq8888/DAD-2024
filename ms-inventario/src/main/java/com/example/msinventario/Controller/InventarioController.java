package com.example.msinventario.Controller;

import com.example.msinventario.Entity.Inventario; // Importa la entidad Inventario
import com.example.msinventario.Service.InventarioService; // Importa el servicio de Inventario
import org.springframework.beans.factory.annotation.Autowired; // Importa la anotación para la inyección de dependencias
import org.springframework.http.ResponseEntity; // Importa la clase para crear respuestas HTTP
import org.springframework.web.bind.annotation.*; // Importa las anotaciones para los controladores REST

import java.util.List; // Importa la clase List
import java.util.Optional; // Importa la clase Optional

@RestController // Indica que esta clase es un controlador REST
@RequestMapping("/inventario") // Define la ruta base para las operaciones del inventario
public class InventarioController {

    @Autowired // Inyecta el servicio de inventario
    private InventarioService inventarioService;

    @GetMapping // Maneja las solicitudes GET a /inventario
    public ResponseEntity<List<Inventario>> list() {
        List<Inventario> inventarios = inventarioService.list(); // Obtiene la lista de inventarios
        return ResponseEntity.ok(inventarios); // Retorna la lista en la respuesta
    }

    @PostMapping // Maneja las solicitudes POST a /inventario
    public ResponseEntity<Inventario> save(@RequestBody Inventario inventario) {
        Inventario savedInventario = inventarioService.save(inventario); // Guarda el nuevo inventario
        return ResponseEntity.ok(savedInventario); // Retorna el inventario guardado
    }

    @PutMapping // Maneja las solicitudes PUT a /inventario
    public ResponseEntity<Inventario> update(@RequestBody Inventario inventario) {
        Inventario updatedInventario = inventarioService.update(inventario); // Actualiza el inventario existente
        return ResponseEntity.ok(updatedInventario); // Retorna el inventario actualizado
    }

    @GetMapping("/{id}") // Maneja las solicitudes GET a /inventario/{id}
    public ResponseEntity<Inventario> listById(@PathVariable Integer id) {
        Optional<Inventario> inventario = inventarioService.findById(id); // Busca el inventario por ID
        return inventario.map(ResponseEntity::ok) // Si se encuentra, retorna el inventario
                .orElseGet(() -> ResponseEntity.notFound().build()); // Si no se encuentra, retorna un 404
    }

    @DeleteMapping("/{id}") // Maneja las solicitudes DELETE a /inventario/{id}
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        inventarioService.delete(id); // Elimina el inventario por ID
        return ResponseEntity.ok("Eliminación Correcta"); // Retorna un mensaje de éxito
    }
}
