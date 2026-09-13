package com.hospital.inventario.repository;

import com.hospital.inventario.model.EntradaInventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntradaInventarioRepository
        extends JpaRepository<EntradaInventario, Long> {
}