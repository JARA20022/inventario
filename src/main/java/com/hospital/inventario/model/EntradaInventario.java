package com.hospital.inventario.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "entrada_inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntradaInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // RF-INV-31: producto asociado a la entrada
    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    // RF-INV-32: temporal hasta integrar la entidad Proveedor
    @Column(name = "proveedor_id", nullable = false)
    private Long proveedorId;

    // RF-INV-33
    @Column(nullable = false)
    private String lote;

    // RF-INV-34
    @Column(name = "fecha_vencimiento", nullable = false)
    private LocalDate fechaVencimiento;

    // RF-INV-35
    @Column(name = "cantidad_recibida", nullable = false)
    private Integer cantidadRecibida;

    // RF-INV-36
    @Column(name = "costo_unitario", nullable = false, precision = 12, scale = 2)
    private BigDecimal costoUnitario;

    @Column(name = "fecha_entrada", nullable = false, updatable = false)
    private LocalDateTime fechaEntrada;

    @PrePersist
    public void asignarFechaEntrada() {
        if (fechaEntrada == null) {
            fechaEntrada = LocalDateTime.now();
        }
    }
}