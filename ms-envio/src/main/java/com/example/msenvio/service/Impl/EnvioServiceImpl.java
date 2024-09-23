package com.example.msenvio.service.Impl;

import com.example.msenvio.entity.Envio; // Importa la entidad Envio
import com.example.msenvio.entity.EnvioDetalle; // Importa la entidad EnvioDetalle
import com.example.msenvio.repository.EnvioRepository; // Importa el repositorio EnvioRepository
import com.example.msenvio.service.EnvioService; // Importa la interfaz de servicio EnvioService
import com.example.msenvio.dto.Cliente; // Importa el DTO Cliente
import com.example.msenvio.dto.Producto; // Importa el DTO Producto
import com.example.msenvio.feign.ClienteFeign; // Importa el cliente Feign para Cliente
import com.example.msenvio.feign.ProductoFeign; // Importa el cliente Feign para Producto
import org.springframework.beans.factory.annotation.Autowired; // Importa la anotación para inyección de dependencias
import org.springframework.stereotype.Service; // Importa la anotación para definir un servicio

import java.util.List; // Importa la clase List
import java.util.Optional; // Importa la clase Optional
import java.util.stream.Collectors; // Importa las herramientas para trabajar con streams

@Service // Indica que esta clase es un servicio
public class EnvioServiceImpl implements EnvioService {

    @Autowired
    private EnvioRepository envioRepository; // Inyección del repositorio de envíos

    @Autowired
    private ProductoFeign productoFeign; // Inyección del cliente Feign para productos

    @Autowired
    private ClienteFeign clienteFeign; // Inyección del cliente Feign para clientes

    @Override
    public List<Envio> list() {
        return envioRepository.findAll(); // Retorna todos los envíos
    }

    @Override
    public Envio save(Envio envio) {
        return envioRepository.save(envio); // Guarda un nuevo envío
    }

    @Override
    public Envio update(Envio envio) {
        return envioRepository.save(envio); // Actualiza un envío existente
    }

    @Override
    public Optional<Envio> findById(Integer id) {
        return envioRepository.findById(id); // Busca un envío por ID
    }

    @Override
    public Optional<Envio> listarPorId(Integer id) {
        Envio envio = envioRepository.findById(id).orElse(null); // Busca el envío
        if (envio == null) {
            return Optional.empty(); // Retorna vacío si no se encuentra
        }

        // Obtiene el cliente asociado al envío usando el cliente Feign
        Cliente cliente = clienteFeign.getById(envio.getClienteId()).getBody();

        // Mapea los detalles de envío para incluir el producto
        List<EnvioDetalle> envioDetalles = envio.getEnvioDetalle().stream().map(envioDetalle -> {
            Producto producto = productoFeign.getById(envioDetalle.getProductoId()).getBody(); // Obtiene el producto por ID
            envioDetalle.setProducto(producto); // Asocia el producto al detalle de envío
            return envioDetalle; // Retorna el detalle modificado
        }).collect(Collectors.toList()); // Colecciona los detalles en una lista

        envio.setEnvioDetalle(envioDetalles); // Asocia la lista de detalles al envío
        envio.setCliente(cliente); // Asocia el cliente al envío
        return Optional.of(envio); // Retorna el envío con datos completos
    }

    @Override
    public void deleteById(Integer id) {
        envioRepository.deleteById(id); // Elimina un envío por ID
    }
}
