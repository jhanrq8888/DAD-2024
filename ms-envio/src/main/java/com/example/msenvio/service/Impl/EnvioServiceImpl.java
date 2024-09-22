package com.example.msenvio.service.Impl;

import com.example.msenvio.entity.Envio;
import com.example.msenvio.repository.EnvioRepository;
import com.example.msenvio.service.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnvioServiceImpl implements EnvioService {

    @Autowired
    private EnvioRepository envioRepository;

    @Override
    public List<Envio> list() {
        return envioRepository.findAll();
    }

    @Override
    public Envio save(Envio envio) {
        return envioRepository.save(envio);
    }

    @Override
    public Envio update(Envio envio) {
        return envioRepository.save(envio);
    }

    @Override
    public Optional<Envio> findById(Integer id) {
        return envioRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        envioRepository.deleteById(id);
    }
}
