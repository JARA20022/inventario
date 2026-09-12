package com.hospital.inventario.controller;

import com.hospital.inventario.model.MovimientoInventario;
import com.hospital.inventario.service.MovimientoInventarioService;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/inventario")
public class MovimientoInventarioController {


    private final MovimientoInventarioService movimientoService;


    public MovimientoInventarioController(
            MovimientoInventarioService movimientoService) {

        this.movimientoService = movimientoService;
    }



    // Registrar entrada
    @PostMapping("/entrada")
    public MovimientoInventario registrarEntrada(
            @RequestParam Long productoId,
            @RequestParam Integer cantidad,
            @RequestParam(required = false) String observacion) {


        return movimientoService.registrarEntrada(
                productoId,
                cantidad,
                observacion
        );

    }





    // Registrar salida
    @PostMapping("/salida")
    public MovimientoInventario registrarSalida(
            @RequestParam Long productoId,
            @RequestParam Integer cantidad,
            @RequestParam(required = false) String observacion) {


        return movimientoService.registrarSalida(
                productoId,
                cantidad,
                observacion
        );

    }




    // Listar movimientos
    @GetMapping("/movimientos")
    public List<MovimientoInventario> listar(){

        return movimientoService.listarMovimientos();

    }




    // Historial de un producto
    @GetMapping("/movimientos/{productoId}")
    public List<MovimientoInventario> historial(
            @PathVariable Long productoId){

        return movimientoService.historialProducto(productoId);

    }

}