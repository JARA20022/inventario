package com.hospital.inventario.service;

import com.hospital.inventario.model.Presentacion;
import com.hospital.inventario.repository.PresentacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PresentacionService {

    private final PresentacionRepository repo;

    public List<Presentacion> listar() {
        return repo.findAll();
    }

    public Presentacion obtener(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public Presentacion guardar(Presentacion p) {
        return repo.save(p);
    }

    public void cambiarEstado(Long id) {
        Presentacion p = obtener(id);
        p.setEstado(!p.isEstado());
        repo.save(p);
    }
}