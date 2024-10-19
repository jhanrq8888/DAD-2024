package com.example.msproducto.service;

import com.example.msproducto.entity.Producto;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<Producto> list();
    public Producto save(Producto Product);
    public Producto update(Producto Product);
    public Optional<Producto> findById(Integer id);
    public void deleteById(Integer id);
}
