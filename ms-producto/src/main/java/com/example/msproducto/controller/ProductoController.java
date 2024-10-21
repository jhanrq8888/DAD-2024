package com.example.msproducto.controller;

import com.example.msproducto.entity.Producto;           // Importa la entidad Producto
import com.example.msproducto.service.ProductService;    // Importa el servicio de productos
import org.springframework.beans.factory.annotation.Autowired;    // Importa la anotación para inyección de dependencias
import org.springframework.http.ResponseEntity;             // Importa la clase para manejar las respuestas HTTP
import org.springframework.web.bind.annotation.*;              // Importa las anotaciones para crear un controlador REST

import java.util.List;                                  // Importa la clase List para manejar colecciones
import java.util.Optional;                              // Importa la clase Optional para manejar valores que pueden estar ausentes

@RestController  // Indica que esta clase es un controlador REST
@RequestMapping("/product")  // Mapeo base para las rutas relacionadas con productos
public class ProductoController {

    @Autowired
    private ProductService productService;  // Servicio para la lógica de negocio relacionada con productos

    // Obtener todos los productos
    @GetMapping
    public ResponseEntity<List<Producto>> list() {
        List<Producto> products = productService.list(); // Llama al servicio para obtener la lista de productos
        return ResponseEntity.ok(products); // Devuelve la lista de productos con estado 200 OK
    }

    // Crear un nuevo producto
    @PostMapping
    public ResponseEntity<Producto> save(@RequestBody Producto product) {
        Producto savedProduct = productService.save(product); // Guarda el nuevo producto a través del servicio
        return ResponseEntity.ok(savedProduct); // Devuelve el producto guardado con estado 200 OK
    }

    // Actualizar un producto existente
    @PutMapping
    public ResponseEntity<Producto> update(@RequestBody Producto product) {
        Producto updatedProduct = productService.update(product); // Actualiza el producto a través del servicio
        return ResponseEntity.ok(updatedProduct); // Devuelve el producto actualizado con estado 200 OK
    }

    // Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> listById(@PathVariable Integer id) {
        Optional<Producto> product = productService.findById(id); // Busca el producto por ID
        return product.map(ResponseEntity::ok) // Si el producto existe, devuelve 200 OK
                .orElseGet(() -> ResponseEntity.notFound().build()); // Si no existe, devuelve 404 Not Found
    }

    // Eliminar un producto por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        productService.deleteById(id); // Elimina el producto a través del servicio
        return ResponseEntity.ok("Eliminación Correcta"); // Devuelve un mensaje de éxito con estado 200 OK
    }
}
