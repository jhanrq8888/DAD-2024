package com.example.msproducto.service.impl;

import com.example.msproducto.entity.Producto;                // Entidad Producto
import com.example.msproducto.repository.ProductoRepository;  // Repositorio Producto
import com.example.msproducto.service.ProductService;         // Servicio Producto
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductoRepository productRepository;

    private final Random random = new Random(); // Generador para códigos automáticos

    // Listar todos los productos
    @Override
    public List<Producto> list() {
        return productRepository.findAll();
    }

    // Guardar un nuevo producto (con lógica de código automático y sin imagen)
    @Override
    public Producto save(Producto product) {
        if (product.getCodigo() == null) {
            product.setCodigo(generateCodigo());
        }
        return productRepository.save(product);
    }

    // Guardar producto con imagen
    public Producto saveWithImage(Producto product, MultipartFile imagen) throws IOException {
        if (product.getCodigo() == null) {
            product.setCodigo(generateCodigo());
        }

        if (imagen != null && !imagen.isEmpty()) {
            String imagePath = saveImage(imagen);
            product.setImagenPath(imagePath);
        }

        return productRepository.save(product);
    }

    // Actualizar un producto existente
    @Override
    public Producto update(Producto product) {
        return productRepository.save(product);
    }

    // Buscar producto por ID
    @Override
    public Optional<Producto> findById(Integer id) {
        return productRepository.findById(id);
    }

    // Eliminar producto por ID
    @Override
    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }

    // Generar código único para el producto
    private Integer generateCodigo() {
        int codigo = 1000 + random.nextInt(9000); // Genera un número entre 1000 y 9999
        while (productRepository.findByCodigo(codigo).isPresent()) {
            codigo = 1000 + random.nextInt(9000); // Genera uno nuevo si ya existe
        }
        return codigo;
    }

    // Subir imagen al servidor
    private String saveImage(MultipartFile imagen) throws IOException {
        String directory = "images/"; // Directorio para almacenar imágenes
        String fileName = UUID.randomUUID() + "_" + imagen.getOriginalFilename();
        File dir = new File(directory);

        if (!dir.exists()) {
            dir.mkdirs(); // Crea el directorio si no existe
        }

        File file = new File(directory + fileName);
        imagen.transferTo(file); // Guarda la imagen en el servidor

        return file.getAbsolutePath(); // Retorna la ruta del archivo guardado
    }
}
