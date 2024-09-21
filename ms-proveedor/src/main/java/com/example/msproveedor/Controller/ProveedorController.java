package com.example.msproveedor.Controller;


import com.example.msproveedor.Entity.Proveedor;
import com.example.msproveedor.Service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/proveedor")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public ResponseEntity<List<Proveedor>> list() {
        List<Proveedor> proveedores = proveedorService.list();
        return ResponseEntity.ok(proveedores);
    }

    @PostMapping
    public ResponseEntity<Proveedor> save(@RequestBody Proveedor proveedor) {
        Proveedor savedProveedor = proveedorService.save(proveedor);
        return ResponseEntity.ok(savedProveedor);
    }

    @PutMapping
    public ResponseEntity<Proveedor> update(@RequestBody Proveedor proveedor) {
        Proveedor updatedProveedor = proveedorService.update(proveedor);
        return ResponseEntity.ok(updatedProveedor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> listById(@PathVariable Integer id) {
        Optional<Proveedor> proveedor = proveedorService.findById(id);
        return proveedor.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        proveedorService.deleteById(id);
        return ResponseEntity.ok("Eliminación Correcta");
    }
}