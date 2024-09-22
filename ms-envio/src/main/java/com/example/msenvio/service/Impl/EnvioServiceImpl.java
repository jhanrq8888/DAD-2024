package com.example.msenvio.service.Impl;

import com.example.msenvio.entity.Envio;
import com.example.msenvio.entity.EnvioDetalle;
import com.example.msenvio.repository.EnvioRepository;
import com.example.msenvio.service.EnvioService;
import com.example.msenvio.dto.Cliente; // Importar Cliente
import com.example.msenvio.dto.Producto;
import com.example.msenvio.feign.ClienteFeign; // Feign client para Cliente
import com.example.msenvio.feign.ProductoFeign; // Feign client para Producto
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnvioServiceImpl implements EnvioService {

    @Autowired
    private EnvioRepository envioRepository;

    @Autowired
    private ProductoFeign productoFeign;

    @Autowired
    private ClienteFeign clienteFeign; // Cliente Feign

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
    public Optional<Envio> listarPorId(Integer id) {
        Envio envio = envioRepository.findById(id).orElse(null);
        if (envio == null) {
            return Optional.empty();
        }

        Cliente cliente = clienteFeign.getById(envio.getClienteId()).getBody(); // Obtener cliente por ID

        List<EnvioDetalle> envioDetalles = envio.getEnvioDetalle().stream().map(envioDetalle -> {
            Producto producto = productoFeign.getById(envioDetalle.getProductoId()).getBody();
            envioDetalle.setProducto(producto);
            return envioDetalle;
        }).collect(Collectors.toList());

        envio.setEnvioDetalle(envioDetalles);
        envio.setCliente(cliente); // Asignar cliente al envío
        return Optional.of(envio);
    }

    @Override
    public void deleteById(Integer id) {
        envioRepository.deleteById(id);
    }
}
