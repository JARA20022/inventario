package com.hospital.inventario.repository;

import com.hospital.inventario.model.Presentacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PresentacionRepository extends JpaRepository<Presentacion, Long> {
}