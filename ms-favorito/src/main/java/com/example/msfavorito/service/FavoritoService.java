package com.example.msfavorito.service;

import com.example.msfavorito.entity.Favorito;

import java.util.List;
import java.util.Optional;

public interface FavoritoService {
    public List<Favorito> list();
    public  Favorito save(Favorito Favorito);
    public Favorito update(Favorito Favorito);
    public Optional<Favorito> findById(Integer id);
    public void deleteById(Integer id);
}
