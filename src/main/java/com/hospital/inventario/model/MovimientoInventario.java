package com.hospital.inventario.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "movimiento_inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoInventario {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;


    @Column(nullable = false)
    private String tipoMovimiento;
    // ENTRADA o SALIDA


    @Column(nullable = false)
    private Integer cantidad;


    private LocalDateTime fecha = LocalDateTime.now();


    private String observacion;

}