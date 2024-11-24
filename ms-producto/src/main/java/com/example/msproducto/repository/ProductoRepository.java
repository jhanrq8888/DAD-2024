package com.example.msproducto.repository;

import com.example.msproducto.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    Optional<Producto> findByCodigo(Integer producto);
}
