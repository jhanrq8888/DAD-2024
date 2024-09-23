package com.example.msfavorito.repository;

import com.example.msfavorito.entity.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritoRepository extends JpaRepository<Favorito, Integer> {
}
