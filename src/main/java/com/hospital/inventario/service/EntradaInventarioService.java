package com.hospital.inventario.service;

import com.hospital.inventario.model.EntradaInventario;
import com.hospital.inventario.model.Producto;
import com.hospital.inventario.repository.EntradaInventarioRepository;
import com.hospital.inventario.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EntradaInventarioService {

    private final EntradaInventarioRepository entradaRepository;
    private final ProductoRepository productoRepository;

    public List<EntradaInventario> listar() {
        return entradaRepository.findAll();
    }

    public EntradaInventario buscarPorId(Long id) {
        return entradaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Entrada no encontrada"
                ));
    }

    public EntradaInventario registrar(EntradaInventario entrada) {
        validar(entrada);

        Producto producto = buscarProducto(entrada);
        entrada.setId(null);
        entrada.setProducto(producto);

        return entradaRepository.save(entrada);
    }

    public EntradaInventario actualizar(
            Long id,
            EntradaInventario datos
    ) {
        validar(datos);

        EntradaInventario entrada = buscarPorId(id);
        Producto producto = buscarProducto(datos);

        entrada.setProducto(producto);
        entrada.setProveedorId(datos.getProveedorId());
        entrada.setLote(datos.getLote());
        entrada.setFechaVencimiento(datos.getFechaVencimiento());
        entrada.setCantidadRecibida(datos.getCantidadRecibida());
        entrada.setCostoUnitario(datos.getCostoUnitario());

        return entradaRepository.save(entrada);
    }

    public void eliminar(Long id) {
        EntradaInventario entrada = buscarPorId(id);
        entradaRepository.delete(entrada);
    }

    private Producto buscarProducto(EntradaInventario entrada) {
        if (entrada.getProducto() == null ||
                entrada.getProducto().getId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Debe indicar el id del producto"
            );
        }

        return productoRepository.findById(entrada.getProducto().getId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Producto no encontrado"
                ));
    }

    private void validar(EntradaInventario entrada) {
        if (entrada.getProveedorId() == null ||
                entrada.getProveedorId() <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El proveedor es obligatorio"
            );
        }

        if (entrada.getLote() == null ||
                entrada.getLote().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El lote es obligatorio"
            );
        }

        if (entrada.getFechaVencimiento() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La fecha de vencimiento es obligatoria"
            );
        }

        if (entrada.getCantidadRecibida() == null ||
                entrada.getCantidadRecibida() <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La cantidad debe ser mayor que cero"
            );
        }

        if (entrada.getCostoUnitario() == null ||
                entrada.getCostoUnitario().compareTo(BigDecimal.ZERO) < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El costo unitario no puede ser negativo"
            );
        }
    }
}