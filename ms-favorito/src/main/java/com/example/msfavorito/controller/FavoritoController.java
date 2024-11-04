package com.example.msfavorito.controller;

import com.example.msfavorito.entity.Favorito;
import com.example.msfavorito.service.FavoritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/favorito")
public class FavoritoController {
    @Autowired
    private FavoritoService favoritoService;
    @GetMapping
    public ResponseEntity<List<Favorito>> list() {
        List<Favorito> favoritos = favoritoService.list();
        return ResponseEntity.ok(favoritos);
    }

    @PostMapping
    public ResponseEntity<Favorito> save(@RequestBody Favorito favorito) {
        Favorito savedFavorito = favoritoService.save(favorito);
        return ResponseEntity.ok(savedFavorito);
    }

    @PutMapping
    public ResponseEntity<Favorito> update(@RequestBody Favorito favorito) {
        Favorito updatedFavorito = favoritoService.update(favorito);
        return ResponseEntity.ok(updatedFavorito);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Favorito> listById(@PathVariable Integer id) {
        Optional<Favorito> favorito = favoritoService.findById(id);
        return favorito.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        favoritoService.deleteById(id);
        return ResponseEntity.ok("Eliminación Correcta");
    }
}
