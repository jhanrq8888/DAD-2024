package com.example.msmetpag.service.Impl;

import com.example.msmetpag.entity.Metpag;
import com.example.msmetpag.repository.MetpagRepository;
import com.example.msmetpag.service.MetpagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MetpagServiceImpl implements MetpagService {

    @Autowired
    private MetpagRepository metpagRepository;

    @Override
    public List<Metpag> list() {
        return metpagRepository.findAll();
    }

    @Override
    public Metpag save(Metpag metpag) {
        return metpagRepository.save(metpag);
    }

    @Override
    public Metpag update(Metpag metpag) {
        return metpagRepository.save(metpag);
    }

    @Override
    public Optional<Metpag> findById(Long id) {
        return metpagRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        metpagRepository.deleteById(id);
    }
}
