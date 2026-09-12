package com.hospital.inventario.controller;

import com.hospital.inventario.model.Categoria;
import com.hospital.inventario.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService service;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("categorias", service.listar());
        return "categoria/lista";
    }

    @GetMapping("/nuevo")
    public String formulario(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categoria/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Categoria categoria) {
        service.guardar(categoria);
        return "redirect:/categorias";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("categoria", service.obtener(id));
        return "categoria/formulario";
    }

    @GetMapping("/estado/{id}")
    public String estado(@PathVariable Long id) {
        service.cambiarEstado(id);
        return "redirect:/categorias";
    }
}