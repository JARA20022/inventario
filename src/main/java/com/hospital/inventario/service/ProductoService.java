package com.hospital.inventario.service;

import com.hospital.inventario.model.Producto;
import com.hospital.inventario.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository repo;

    public List<Producto> listar() {
        return repo.findAll();
    }

    public Producto obtener(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public Producto guardar(Producto p) {
        return repo.save(p);
    }

    public void cambiarEstado(Long id) {
        Producto p = obtener(id);
        p.setEstado(!p.isEstado());
        repo.save(p);
    }
}