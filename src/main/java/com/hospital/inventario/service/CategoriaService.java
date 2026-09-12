package com.hospital.inventario.service;

import com.hospital.inventario.model.Categoria;
import com.hospital.inventario.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repo;

    public List<Categoria> listar() {
        return repo.findAll();
    }

    public Categoria obtener(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public Categoria guardar(Categoria c) {
        return repo.save(c);
    }

    public void cambiarEstado(Long id) {
        Categoria c = obtener(id);
        c.setEstado(!c.isEstado());
        repo.save(c);
    }
}