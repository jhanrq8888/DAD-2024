package com.example.mspedido.service.impl;

import com.example.mspedido.dto.ClienteDto;
import com.example.mspedido.dto.ProductoDto;
import com.example.mspedido.entity.Pedido;
import com.example.mspedido.entity.PedidoDetalle;
import com.example.mspedido.feign.ClienteFeign;
import com.example.mspedido.feign.ProductoFeign;
import com.example.mspedido.repository.PedidoRepository;
import com.example.mspedido.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteFeign clienteFeign;

    @Autowired
    private ProductoFeign productoFeign;

    @Override
    public List<Pedido> list() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido save(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido update(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public Optional<Pedido> findById(Integer id) {
        return pedidoRepository.findById(id);
    }

    @Override
    public Optional<Pedido> listarPorId(Integer id) {
        Optional<Pedido> optionalPedido = pedidoRepository.findById(id);
        if (optionalPedido.isPresent()) {
            Pedido pedido = optionalPedido.get();
            ResponseEntity<ClienteDto> clienteResponse = clienteFeign.listById(pedido.getClienteId());
            ClienteDto cliente = clienteResponse.getBody();
            List<PedidoDetalle> pedidoDetalles = pedido.getDetalle().stream().map(pedidoDetalle -> {
                ResponseEntity<ProductoDto> productoResponse = productoFeign.listById(pedidoDetalle.getProductoId());
                ProductoDto producto = productoResponse.getBody();
                pedidoDetalle.setProducto(producto);
                return pedidoDetalle;
            }).collect(Collectors.toList());
            pedido.setDetalle(pedidoDetalles);
            pedido.setCliente(cliente);
            return Optional.of(pedido);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void deleteById(Integer id) {
        pedidoRepository.deleteById(id);
    }
}
