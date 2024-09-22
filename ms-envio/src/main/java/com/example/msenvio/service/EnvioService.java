package com.example.msenvio.service;

import com.example.msenvio.entity.Envio;

import java.util.List;
import java.util.Optional;

public interface EnvioService {
    public List<Envio> list();
    public Envio save(Envio Envio);
    public Envio update(Envio Envio);
    public Optional<Envio> findById(Integer id);
    public void deleteById(Integer id);
}