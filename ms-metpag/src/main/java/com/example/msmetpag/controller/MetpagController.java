package com.example.msmetpag.controller;

import com.example.msmetpag.entity.Metpag;
import com.example.msmetpag.service.MetpagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/metodos-pago")
public class MetpagController {

    @Autowired
    private MetpagService metpagService;

    @GetMapping
    public ResponseEntity<List<Metpag>> list() {
        List<Metpag> metpagos = metpagService.list();
        return ResponseEntity.ok(metpagos);
    }

    @PostMapping
    public ResponseEntity<Metpag> save(@RequestBody Metpag metpag) {
        Metpag savedMetpag = metpagService.save(metpag);
        return ResponseEntity.ok(savedMetpag);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Metpag> update(@PathVariable Long id, @RequestBody Metpag metpag) {
        metpag.setId(id); // Aseguramos que el ID sea el correcto al actualizar
        Metpag updatedMetpag = metpagService.update(metpag);
        return ResponseEntity.ok(updatedMetpag);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Metpag> listById(@PathVariable Long id) {
        Optional<Metpag> metpag = metpagService.findById(id);
        return metpag.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        metpagService.deleteById(id);
        return ResponseEntity.ok("Eliminación Correcta");
    }
}
