package com.hospital.inventario.controller;

import com.hospital.inventario.model.EntradaInventario;
import com.hospital.inventario.service.EntradaInventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entradas")
@RequiredArgsConstructor
public class EntradaInventarioController {

    private final EntradaInventarioService entradaService;

    @GetMapping
    public List<EntradaInventario> listar() {
        return entradaService.listar();
    }

    @GetMapping("/{id}")
    public EntradaInventario buscarPorId(@PathVariable Long id) {
        return entradaService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<EntradaInventario> registrar(
            @RequestBody EntradaInventario entrada
    ) {
        EntradaInventario registrada =
                entradaService.registrar(entrada);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(registrada);
    }

    @PutMapping("/{id}")
    public EntradaInventario actualizar(
            @PathVariable Long id,
            @RequestBody EntradaInventario entrada
    ) {
        return entradaService.actualizar(id, entrada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        entradaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}