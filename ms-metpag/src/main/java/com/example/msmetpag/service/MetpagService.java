package com.example.msmetpag.service;

import com.example.msmetpag.entity.Metpag;

import java.util.List;
import java.util.Optional;

public interface MetpagService {
    List<Metpag> list();
    Metpag save(Metpag metpag);
    Metpag update(Metpag metpag);
    Optional<Metpag> findById(Long id);
    void deleteById(Long id);
}
