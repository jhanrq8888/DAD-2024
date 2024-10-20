package com.example.msproducto.controller;

import com.example.msproducto.entity.Producto;
import com.example.msproducto.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductoController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Producto>> list() {
        List<Producto> products = productService.list();
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<Producto> save(@RequestBody Producto product) {
        Producto savedProduct = productService.save(product);
        return ResponseEntity.ok(savedProduct);
    }

    @PutMapping
    public ResponseEntity<Producto> update(@RequestBody Producto product) {
        Producto updatedProduct = productService.update(product);
        return ResponseEntity.ok(updatedProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> listById(@PathVariable Integer id) {
        Optional<Producto> product = productService.findById(id);
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        productService.deleteById(id);
        return ResponseEntity.ok("Eliminación Correcta");
    }
}
