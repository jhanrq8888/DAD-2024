package com.example.msproducto.service.impl;
import com.example.msproducto.entity.Producto;
import com.example.msproducto.repository.ProductoRepository;
import com.example.msproducto.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class    ProductServiceImpl implements ProductService {

    @Autowired
    private ProductoRepository productRepository;

    @Override
    public List<Producto> list() {
        return productRepository.findAll();
    }

    @Override
    public Producto save(Producto product) {
        return productRepository.save(product);
    }

    @Override
    public Producto update(Producto product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Producto> findById(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }
}
