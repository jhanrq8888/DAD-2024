package com.example.msinventario.Service;

import com.example.msinventario.Entity.Inventario;

import java.util.List;
import java.util.Optional;

public interface InventarioService {
    public List<Inventario> list();
    public Inventario save(Inventario inventario);
    public Inventario update(Inventario inventario);
    public Optional<Inventario> findById(Integer id);

    Optional<Inventario> listarPorId(Integer id);

    public void deleteById(Integer id);
}