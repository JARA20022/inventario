package com.hospital.inventario.controller;

import com.hospital.inventario.model.Producto;
import com.hospital.inventario.service.MovimientoInventarioService;
import com.hospital.inventario.repository.ProductoRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/inventario")
public class InventarioWebController {


    private final MovimientoInventarioService movimientoService;
    private final ProductoRepository productoRepository;


    public InventarioWebController(
            MovimientoInventarioService movimientoService,
            ProductoRepository productoRepository) {

        this.movimientoService = movimientoService;
        this.productoRepository = productoRepository;
    }



    @GetMapping
    public String inventario(Model model){

        var productos = productoRepository.findAll();

        var movimientos = movimientoService.listarMovimientos();

        var stockBajo = productoRepository.productosConStockBajo();


        model.addAttribute(
                "productos",
                productos
        );


        model.addAttribute(
                "movimientos",
                movimientos
        );


        model.addAttribute(
                "alertas",
                stockBajo
        );


        // Dashboard

        model.addAttribute(
                "totalProductos",
                productos.size()
        );


        model.addAttribute(
                "totalMovimientos",
                movimientos.size()
        );


        model.addAttribute(
                "totalStockBajo",
                stockBajo.size()
        );


        return "inventario/index";
    }



    // Formulario entrada
    @GetMapping("/entrada")
    public String entrada(Model model){

        model.addAttribute(
                "productos",
                productoRepository.findAll()
        );

        return "inventario/entrada";
    }




    // Guardar entrada
    @PostMapping("/entrada")
    public String guardarEntrada(
            @RequestParam Long productoId,
            @RequestParam Integer cantidad,
            @RequestParam String observacion){


        movimientoService.registrarEntrada(
                productoId,
                cantidad,
                observacion
        );


        return "redirect:/inventario";
    }





    // Formulario salida
    @GetMapping("/salida")
    public String salida(Model model){

        model.addAttribute(
                "productos",
                productoRepository.findAll()
        );

        return "inventario/salida";
    }



    // Guardar salida
    @PostMapping("/salida")
    public String guardarSalida(
            @RequestParam Long productoId,
            @RequestParam Integer cantidad,
            @RequestParam String observacion){


        movimientoService.registrarSalida(
                productoId,
                cantidad,
                observacion
        );


        return "redirect:/inventario";
    }

}