package com.hospital.inventario.controller;

import com.hospital.inventario.model.UnidadMedida;
import com.hospital.inventario.service.UnidadMedidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/unidades")
@RequiredArgsConstructor
public class UnidadMedidaController {

    private final UnidadMedidaService service;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("unidades", service.listar());
        return "unidadmedida/lista";
    }

    @GetMapping("/nuevo")
    public String formulario(Model model) {
        model.addAttribute("unidad", new UnidadMedida());
        return "unidadmedida/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute UnidadMedida unidad) {
        service.guardar(unidad);
        return "redirect:/unidades";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("unidad", service.obtener(id));
        return "unidadmedida/formulario";
    }

    @GetMapping("/estado/{id}")
    public String estado(@PathVariable Long id) {
        service.cambiarEstado(id);
        return "redirect:/unidades";
    }
}