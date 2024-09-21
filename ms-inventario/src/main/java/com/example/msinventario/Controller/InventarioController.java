package com.example.msinventario.Controller;

import com.example.msinventario.Entity.Inventario;

import com.example.msinventario.Service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    public ResponseEntity<List<Inventario>> list() {
        List<Inventario> inventarios = inventarioService.list();
        return ResponseEntity.ok(inventarios);
    }

    @PostMapping
    public ResponseEntity<Inventario> save(@RequestBody Inventario inventario) {
        Inventario savedInventario = inventarioService.save(inventario);
        return ResponseEntity.ok(savedInventario);
    }

    @PutMapping
    public ResponseEntity<Inventario> update(@RequestBody Inventario inventario) {
        Inventario updatedInventario = inventarioService.update(inventario);
        return ResponseEntity.ok(updatedInventario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventario> listById(@PathVariable Integer id) {
        Optional<Inventario> inventario = inventarioService.findById(id);
        return inventario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        inventarioService.deleteById(id);
        return ResponseEntity.ok("Eliminación Correcta");
    }
}