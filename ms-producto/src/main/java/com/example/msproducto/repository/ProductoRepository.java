package com.example.msproducto.repository;

import com.example.msproducto.entity.Producto;          // Importa la entidad Producto
import org.springframework.data.jpa.repository.JpaRepository; // Importa la interfaz base para repositorios JPA

// Interfaz del repositorio para la entidad Producto
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    // JpaRepository proporciona métodos CRUD predefinidos para la entidad Producto
    // No es necesario agregar métodos adicionales a menos que se necesiten consultas específicas
}
