package com.hospital.inventario.model;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "presentacion")
@Data @NoArgsConstructor @AllArgsConstructor
public class Presentacion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nombre;
    private boolean estado = true;
}