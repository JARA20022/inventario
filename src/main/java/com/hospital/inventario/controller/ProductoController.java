package com.hospital.inventario.controller;

import com.hospital.inventario.model.Producto;
import com.hospital.inventario.repository.CategoriaRepository;
import com.hospital.inventario.repository.PresentacionRepository;
import com.hospital.inventario.repository.UnidadMedidaRepository;
import com.hospital.inventario.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService service;
    private final CategoriaRepository categoriaRepo;
    private final UnidadMedidaRepository unidadRepo;
    private final PresentacionRepository presentacionRepo;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("productos", service.listar());
        return "producto/lista";
    }

    @GetMapping("/nuevo")
    public String formulario(Model model) {
        model.addAttribute("producto", new Producto());
        cargarCombos(model);
        return "producto/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Producto producto) {
        service.guardar(producto);
        return "redirect:/productos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("producto", service.obtener(id));
        cargarCombos(model);
        return "producto/formulario";
    }

    @GetMapping("/estado/{id}")
    public String estado(@PathVariable Long id) {
        service.cambiarEstado(id);
        return "redirect:/productos";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("categorias", categoriaRepo.findAll());
        model.addAttribute("unidades", unidadRepo.findAll());
        model.addAttribute("presentaciones", presentacionRepo.findAll());
    }
}