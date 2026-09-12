package com.hospital.inventario.controller;

import com.hospital.inventario.model.Presentacion;
import com.hospital.inventario.service.PresentacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/presentaciones")
@RequiredArgsConstructor
public class PresentacionController {

    private final PresentacionService service;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("presentaciones", service.listar());
        return "presentacion/lista";
    }

    @GetMapping("/nuevo")
    public String formulario(Model model) {
        model.addAttribute("presentacion", new Presentacion());
        return "presentacion/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Presentacion presentacion) {
        service.guardar(presentacion);
        return "redirect:/presentaciones";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("presentacion", service.obtener(id));
        return "presentacion/formulario";
    }

    @GetMapping("/estado/{id}")
    public String estado(@PathVariable Long id) {
        service.cambiarEstado(id);
        return "redirect:/presentaciones";
    }
}