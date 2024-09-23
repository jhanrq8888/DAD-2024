package com.example.msfavorito.service.Impl;

import com.example.msfavorito.entity.Favorito;
import com.example.msfavorito.repository.FavoritoRepository;
import com.example.msfavorito.service.FavoritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoritoServiceImpl implements FavoritoService {

    @Autowired
    private FavoritoRepository favoritoRepository;

    @Override
    public List<Favorito> list() {
        return favoritoRepository.findAll();
    }

    @Override
    public Favorito save(Favorito favorito) {
        return favoritoRepository.save(favorito);
    }

    @Override
    public Favorito update(Favorito favorito) {
        return favoritoRepository.save(favorito);
    }

    @Override
    public Optional<Favorito> findById(Integer id) {
        return favoritoRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        favoritoRepository.deleteById(id);
    }
}
