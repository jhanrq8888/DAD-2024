package com.example.msinventario.feign;

import com.example.msinventario.dto.ProveedorDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-Proveedor-service", path = "/proveedor")
public interface ProveedorFeign {

    @GetMapping("/{id}")
    @CircuitBreaker(name = "proveedorListByIdCB", fallbackMethod = "proveedorListById")
    public ResponseEntity<ProveedorDto> getById(@PathVariable Integer id);

    default ResponseEntity<ProveedorDto> proveedorListByIdFallback(Integer id, Exception e) {
        // Puedes retornar un objeto por defecto en caso de que falle el servicio
        return ResponseEntity.ok(new ProveedorDto());
    }
}
