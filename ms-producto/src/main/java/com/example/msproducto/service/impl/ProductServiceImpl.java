package com.example.msproducto.service.impl;

import com.example.msproducto.entity.Producto;
import com.example.msproducto.repository.ProductoRepository;
import com.example.msproducto.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Producto save(Producto product) {
        return productoRepository.save(product);
    }

    // Buscar un producto por ID
    @Override
    public Optional<Producto> findById(Integer id) {
        return productoRepository.findById(id);
    }

    // Actualizar un producto existente
    @Override
    public Producto update(Producto product) {
        // Asegurarse de que el producto existe antes de actualizar
        if (!productoRepository.existsById(product.getId())) {
            throw new RuntimeException("Producto no encontrado con ID: " + product.getId());
        }
        return productoRepository.save(product);
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
