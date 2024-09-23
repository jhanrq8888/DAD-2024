package com.example.msfavorito.service;

import com.example.msfavorito.entity.Favorito;

import java.util.List;
import java.util.Optional;

public interface FavoritoService {
    List<Favorito> list();
    Favorito save(Favorito favorito);
    Favorito update(Favorito favorito);
    Optional<Favorito> findById(Integer id);
    void deleteById(Integer id);
}
