package com.hospital.inventario.controller;

import com.hospital.inventario.model.EntradaInventario;
import com.hospital.inventario.model.Producto;
import com.hospital.inventario.repository.ProductoRepository;
import com.hospital.inventario.service.EntradaInventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/entradas")
@RequiredArgsConstructor
public class EntradaVistaController {

    private final EntradaInventarioService entradaService;
    private final ProductoRepository productoRepository;

    @GetMapping
    public String mostrarEntradas(Model model) {
        EntradaInventario formulario = new EntradaInventario();
        formulario.setProducto(new Producto());

        model.addAttribute("entrada", formulario);
        model.addAttribute("entradas", entradaService.listar());
        model.addAttribute("productos", productoRepository.findAll());

        return "entradas/entradas";
    }

    @PostMapping("/guardar")
    public String guardar(
            @ModelAttribute("entrada") EntradaInventario entrada
    ) {
        entradaService.registrar(entrada);
        return "redirect:/entradas";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        entradaService.eliminar(id);
        return "redirect:/entradas";
    }
}