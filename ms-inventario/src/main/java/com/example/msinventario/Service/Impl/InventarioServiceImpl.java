package com.example.msinventario.Service.Impl;


import com.example.msinventario.Entity.Inventario;
import com.example.msinventario.Entity.InventarioDetalle;
import com.example.msinventario.Repository.InventarioRepository;
import com.example.msinventario.Service.InventarioService;
import com.example.msinventario.dto.Producto;
import com.example.msinventario.dto.Proveedor;
import com.example.msinventario.feign.ProductoFeign;
import com.example.msinventario.feign.ProveedorFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventarioServiceImpl implements InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private ProductoFeign productoFeign;

    @Autowired
    private ProveedorFeign proveedorFeign;

    @Override
    public List<Inventario> list() {
        return inventarioRepository.findAll();
    }

    @Override
    public Inventario save(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    @Override
    public Inventario update(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    @Override
    public Optional<Inventario> findById(Integer id) {
        return inventarioRepository.findById(id);
    }

    @Override
    public Optional<Inventario> listarPorId(Integer id) {
        Inventario inventario = inventarioRepository.findById(id).get();
        Proveedor proveedor = proveedorFeign.listById(inventario.getProveedorId()).getBody();

        List<InventarioDetalle> inventarioDetalles = inventario.getInventarioDetalle().stream().map(inventarioDetalle -> {
            Producto producto = productoFeign.listById(inventarioDetalle.getProductoId()).getBody();
            inventarioDetalle.setProducto(producto);
            return inventarioDetalle;
        }).collect(Collectors.toList());

        inventario.setInventarioDetalle(inventarioDetalles);
        inventario.setProveedor(proveedor);
        return Optional.of(inventario);
    }

    @Override
    public void deleteById(Integer id) {
        inventarioRepository.deleteById(id);
    }
}