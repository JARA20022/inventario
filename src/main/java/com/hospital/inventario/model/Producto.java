package com.hospital.inventario.model;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "producto")
@Data @NoArgsConstructor @AllArgsConstructor
public class Producto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String codigo;
    @Column(nullable = false)
    private String nombre;
    private String descripcion;

    @ManyToOne @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    @ManyToOne @JoinColumn(name = "unidad_medida_id")
    private UnidadMedida unidadMedida;
    @ManyToOne @JoinColumn(name = "presentacion_id")
    private Presentacion presentacion;

    private Integer stockMinimo;
    private Integer stockMaximo;
    private boolean estado = true;
}