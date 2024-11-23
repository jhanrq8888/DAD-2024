package com.example.msproducto.service;

import com.example.msproducto.entity.Producto;
import org.springframework.web.multipart.MultipartFile; // Importa la clase MultipartFile para manejar archivos
import java.io.IOException;                         // Importa IOException para manejar errores de archivo
import java.util.List;
import java.util.Optional;

// Interfaz del servicio para manejar las operaciones CRUD relacionadas con los productos
public interface ProductService {

    // Método para listar todos los productos
    public List<Producto> list();

    // Método para guardar un nuevo producto
    public Producto save(Producto product);

    // Método para guardar un producto con una imagen asociada
    public Producto saveWithImage(Producto product, MultipartFile imagen) throws IOException;

    // Método para actualizar un producto existente
    public Producto update(Producto product);

    // Método para buscar un producto por su ID
    public Optional<Producto> findById(Integer id);

    // Método para eliminar un producto por su ID
    public void deleteById(Integer id);
}
