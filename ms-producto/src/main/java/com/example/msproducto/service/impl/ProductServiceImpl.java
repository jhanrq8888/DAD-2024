package com.example.msproducto.service.impl;

import com.example.msproducto.entity.Producto;                // Importa la entidad Producto
import com.example.msproducto.repository.ProductoRepository;  // Importa el repositorio de productos
import com.example.msproducto.service.ProductService;         // Importa la interfaz del servicio de productos
import org.springframework.beans.factory.annotation.Autowired;   // Importa la anotación para inyección de dependencias
import org.springframework.stereotype.Service;                  // Importa la anotación para definir esta clase como un servicio

import java.util.List;                                        // Importa la clase List para manejar colecciones
import java.util.Optional;                                    // Importa la clase Optional para manejar valores que pueden estar ausentes

@Service  // Define esta clase como un servicio gestionado por Spring
public class ProductServiceImpl implements ProductService {

    @Autowired  // Inyección de dependencias para utilizar el repositorio de productos
    private ProductoRepository productRepository;

    // Método para listar todos los productos
    @Override
    public List<Producto> list() {
        return productRepository.findAll();  // Llama al repositorio para obtener todos los productos
    }

    // Método para guardar un nuevo producto
    @Override
    public Producto save(Producto product) {
        return productRepository.save(product);  // Llama al repositorio para guardar el producto
    }

    // Método para actualizar un producto existente
    @Override
    public Producto update(Producto product) {
        return productRepository.save(product);  // Llama al repositorio para actualizar el producto
    }

    // Método para buscar un producto por ID
    @Override
    public Optional<Producto> findById(Integer id) {
        return productRepository.findById(id);  // Llama al repositorio para buscar el producto por su ID
    }

    // Método para eliminar un producto por ID
    @Override
    public void deleteById(Integer id) {
        productRepository.deleteById(id);  // Llama al repositorio para eliminar el producto por su ID
    }
}
