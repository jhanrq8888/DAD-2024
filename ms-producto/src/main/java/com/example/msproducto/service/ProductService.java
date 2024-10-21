package com.example.msproducto.service;

import com.example.msproducto.entity.Producto;  // Importa la entidad Producto
import java.util.List;                        // Importa la clase List para manejar colecciones
import java.util.Optional;                    // Importa la clase Optional para manejar valores que pueden estar ausentes

// Interfaz del servicio para manejar las operaciones CRUD relacionadas con los productos
public interface ProductService {

    // Método para listar todos los productos
    public List<Producto> list();

    // Método para guardar un nuevo producto
    public Producto save(Producto product);

    // Método para actualizar un producto existente
    public Producto update(Producto product);

    // Método para buscar un producto por su ID
    public Optional<Producto> findById(Integer id);

    // Método para eliminar un producto por su ID
    public void deleteById(Integer id);
}
