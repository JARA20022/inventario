package com.hospital.inventario.model;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "unidad_medida")
@Data @NoArgsConstructor @AllArgsConstructor
public class UnidadMedida {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String nombre;
    private boolean estado = true;
}