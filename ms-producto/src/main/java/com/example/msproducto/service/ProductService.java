package com.example.msproducto.service;

import com.example.msproducto.entity.Producto;
import org.springframework.web.multipart.MultipartFile; // Para manejar archivos
import java.io.IOException;                         // Para manejar errores de archivo
import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Producto> list();
    Producto save(Producto product);
    Producto saveWithImage(Producto product, MultipartFile imagen) throws IOException;
    Optional<Producto> findById(Integer id);
    Producto update(Producto product);
    void deleteById(Integer id);
}
