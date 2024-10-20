package com.example.msenvio.controller;

import com.example.msenvio.dto.ClienteDto;
import com.example.msenvio.dto.ErrorResponseDto;
import com.example.msenvio.dto.ProductoDto;
import com.example.msenvio.entity.Envio;
import com.example.msenvio.entity.EnvioDetalle;
import com.example.msenvio.feign.ProductoFeign;
import com.example.msenvio.feign.ClienteFeign;
import com.example.msenvio.service.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/envio")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @Autowired
    private ProductoFeign productoFeign;

    @Autowired
    private ClienteFeign clienteFeign;

    @GetMapping
    public ResponseEntity<List<Envio>> getAll() {
        return ResponseEntity.ok(envioService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Envio>> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(envioService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Envio envio) {
        ClienteDto clienteDto = clienteFeign.getById(envio.getClienteId()).getBody();

        if (clienteDto == null || clienteDto.getId() == null) {
            String errorMessage = "Error: Cliente no encontrado.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(errorMessage));
        }
        for (EnvioDetalle envioDetalle : envio.getEnvioDetalle()) {
            ProductoDto productoDto = productoFeign.getById(envioDetalle.getProductoId()).getBody();

            if (productoDto == null || productoDto.getId() == null) {
                String errorMessage = "Error: Producto no encontrado.";
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(errorMessage));
            }
        }
        Envio nuevoEnvio = envioService.save(envio);
        return ResponseEntity.ok(nuevoEnvio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Envio> update(@PathVariable Integer id, @RequestBody Envio envio) {
        envio.setId(id);
        return ResponseEntity.ok(envioService.save(envio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Envio>> delete(@PathVariable Integer id) {
        envioService.delete(id);
        return ResponseEntity.ok(envioService.list());
    }
}
