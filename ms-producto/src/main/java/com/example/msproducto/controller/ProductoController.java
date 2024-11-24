package com.example.msproducto.controller;

import com.example.msproducto.entity.Producto;
import com.example.msproducto.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductoController {

    @Autowired
    private ProductService productService;

    // Obtener todos los productos
    @GetMapping
    public ResponseEntity<List<Producto>> list() {
        List<Producto> products = productService.list();
        return ResponseEntity.ok(products);
    }

    // Crear un nuevo producto con imagen
    @PostMapping
    public ResponseEntity<Producto> save(
            @RequestPart("producto") Producto producto,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen) {

        if (imagen != null && !imagen.isEmpty()) {
            try {
                byte[] imagenBytes = imagen.getBytes();  // Convertir la imagen a bytes
                producto.setImagen(imagenBytes);         // Establecer los bytes de la imagen en el producto
            } catch (IOException e) {
                return ResponseEntity.internalServerError().build();  // Manejar error de lectura de la imagen
            }
        }

        Producto savedProduct = productService.save(producto);  // Guardar el producto en la base de datos
        return ResponseEntity.ok(savedProduct);  // Devolver el producto guardado
    }

    // Actualizar un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<Producto> update(
            @PathVariable Integer id,
            @RequestPart("producto") Producto producto,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen) {

        if (imagen != null && !imagen.isEmpty()) {
            try {
                byte[] imagenBytes = imagen.getBytes();  // Convertir la imagen a bytes
                producto.setImagen(imagenBytes);         // Establecer los bytes de la imagen en el producto
            } catch (IOException e) {
                return ResponseEntity.internalServerError().build();  // Manejar error de lectura de la imagen
            }
        }

        Producto updatedProduct = productService.update( producto);  // Asegúrate de que el service pueda manejar la actualización con ID
        return ResponseEntity.ok(updatedProduct);  // Devolver el producto actualizado
    }

    // Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> listById(@PathVariable Integer id) {
        Optional<Producto> product = productService.findById(id);  // Buscar el producto por ID
        return product.map(ResponseEntity::ok)  // Si el producto existe, devolverlo con estado 200 OK
                .orElseGet(() -> ResponseEntity.notFound().build());  // Si no existe, devolver 404 Not Found
    }

    // Eliminar un producto por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        productService.deleteById(id);  // Eliminar el producto por ID
        return ResponseEntity.ok("Eliminación Correcta");  // Devolver mensaje de éxito
    }
}
