package com.example.msenvio.controller;

import com.example.msenvio.entity.Envio;
import com.example.msenvio.service.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/envio")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @GetMapping
    public ResponseEntity<List<Envio>> list() {
        List<Envio> envios = envioService.list();
        return ResponseEntity.ok(envios);
    }

    @PostMapping
    public ResponseEntity<Envio> save(@RequestBody Envio envio) {
        Envio savedEnvio = envioService.save(envio);
        return ResponseEntity.ok(savedEnvio);
    }

    @PutMapping
    public ResponseEntity<Envio> update(@RequestBody Envio envio) {
        Envio updatedEnvio = envioService.update(envio);
        return ResponseEntity.ok(updatedEnvio);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Envio> listById(@PathVariable Integer id) {
        Optional<Envio> envio = envioService.findById(id);
        return envio.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        envioService.deleteById(id);
        return ResponseEntity.ok("Eliminación Correcta");
    }
}
