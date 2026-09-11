package com.hospital.inventario.service;

import com.hospital.inventario.model.UnidadMedida;
import com.hospital.inventario.repository.UnidadMedidaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnidadMedidaService {

    private final UnidadMedidaRepository repo;

    public List<UnidadMedida> listar() {
        return repo.findAll();
    }

    public UnidadMedida obtener(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public UnidadMedida guardar(UnidadMedida u) {
        return repo.save(u);
    }

    public void cambiarEstado(Long id) {
        UnidadMedida u = obtener(id);
        u.setEstado(!u.isEstado());
        repo.save(u);
    }
}