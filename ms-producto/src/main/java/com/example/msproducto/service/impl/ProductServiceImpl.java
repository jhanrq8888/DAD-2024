package com.example.msproducto.service.impl;

import com.example.msproducto.entity.Producto;
import com.example.msproducto.repository.ProductoRepository;
import com.example.msproducto.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductoRepository productoRepository;

    // Listar todos los productos
    @Override
    public List<Producto> list() {
        return productoRepository.findAll();
    }

    // Guardar un producto (con o sin imagen)
    @Override
    public Producto save(Producto product, MultipartFile imagen) throws IOException {
        if (imagen != null && !imagen.isEmpty()) {
            byte[] imagenBytes = imagen.getBytes();  // Convertir la imagen a bytes
            product.setImagen(imagenBytes);  // Establecer la imagen en el producto
        }
        return productoRepository.save(product);  // Guardar el producto en la base de datos
    }

    // Buscar un producto por ID
    @Override
    public Optional<Producto> findById(Integer id) {
        return productoRepository.findById(id);
    }

    // Actualizar un producto existente (con o sin imagen)
    @Override
    public Producto update(Producto product, MultipartFile imagen) throws IOException {
        // Asegurarse de que el producto existe antes de actualizar
        if (!productoRepository.existsById(product.getId())) {
            throw new RuntimeException("Producto no encontrado con ID: " + product.getId());
        }
        if (imagen != null && !imagen.isEmpty()) {
            byte[] imagenBytes = imagen.getBytes();  // Convertir la imagen a bytes
            product.setImagen(imagenBytes);  // Establecer la nueva imagen en el producto
        }
        return productoRepository.save(product);  // Guardar el producto actualizado en la base de datos
    }

    // Eliminar un producto por ID
    @Override
    public void deleteById(Integer id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }
        productoRepository.deleteById(id);
    }
}
