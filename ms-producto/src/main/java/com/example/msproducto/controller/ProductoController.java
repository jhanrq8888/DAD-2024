package com.example.msproducto.controller;

import com.example.msproducto.entity.Producto;
import com.example.msproducto.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
                String imagenPath = saveImage(imagen);
                producto.setImagenPath(imagenPath);
            } catch (IOException e) {
                return ResponseEntity.internalServerError().build();
            }
        }

        Producto savedProduct = productService.save(producto);
        return ResponseEntity.ok(savedProduct);
    }

    // Subir y guardar la imagen
    private String saveImage(MultipartFile imagen) throws IOException {
        String directory = "images/"; // Carpeta donde se guardarán las imágenes
        String fileName = UUID.randomUUID() + "_" + imagen.getOriginalFilename();
        File imageFile = new File(directory);

        if (!imageFile.exists()) {
            imageFile.mkdirs(); // Crear directorio si no existe
        }

        File file = new File(directory + fileName);
        imagen.transferTo(file);

        return file.getAbsolutePath(); // Retornar la ruta de la imagen
    }

    // Actualizar un producto existente
    @PutMapping
    public ResponseEntity<Producto> update(@RequestBody Producto product) {
        Producto updatedProduct = productService.update(product);
        return ResponseEntity.ok(updatedProduct);
    }

    // Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> listById(@PathVariable Integer id) {
        Optional<Producto> product = productService.findById(id);
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar un producto por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        productService.deleteById(id);
        return ResponseEntity.ok("Eliminación Correcta");
    }
}
