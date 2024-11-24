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
        try {
            if (imagen != null && !imagen.isEmpty()) {
                producto.setImagen(imagen.getBytes());
            }
            Producto savedProduct = productService.save(producto);
            return ResponseEntity.ok(savedProduct);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    // Actualizar un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<Producto> update(
            @PathVariable Integer id,
            @RequestPart("producto") Producto producto,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen) {
        Optional<Producto> existingProduct = productService.findById(id);
        if (!existingProduct.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        try {
            if (imagen != null && !imagen.isEmpty()) {
                producto.setImagen(imagen.getBytes());
            }
            producto.setId(id); // Asegurar que el ID sea el correcto
            Producto updatedProduct = productService.update(producto);
            return ResponseEntity.ok(updatedProduct);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    // Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> listById(@PathVariable Integer id) {
        Optional<Producto> product = productService.findById(id);
        return product.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar un producto por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        if (productService.findById(id).isPresent()) {
            productService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
