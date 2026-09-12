package com.hospital.inventario.service;

import com.hospital.inventario.model.MovimientoInventario;
import com.hospital.inventario.model.Producto;
import com.hospital.inventario.repository.MovimientoInventarioRepository;
import com.hospital.inventario.repository.ProductoRepository;
import com.hospital.inventario.exception.StockInsuficienteException;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class MovimientoInventarioService {


    private final MovimientoInventarioRepository movimientoRepository;
    private final ProductoRepository productoRepository;


    public MovimientoInventarioService(
            MovimientoInventarioRepository movimientoRepository,
            ProductoRepository productoRepository) {

        this.movimientoRepository = movimientoRepository;
        this.productoRepository = productoRepository;
    }



    // Registrar entrada de productos
    public MovimientoInventario registrarEntrada(
            Long productoId,
            Integer cantidad,
            String observacion) {


        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() ->
                        new RuntimeException("Producto no encontrado"));


        producto.setStockActual(
                producto.getStockActual() + cantidad
        );


        productoRepository.save(producto);


        MovimientoInventario movimiento =
                new MovimientoInventario();

        movimiento.setProducto(producto);
        movimiento.setTipoMovimiento("ENTRADA");
        movimiento.setCantidad(cantidad);
        movimiento.setFecha(LocalDateTime.now());
        movimiento.setObservacion(observacion);


        return movimientoRepository.save(movimiento);
    }





    // Registrar salida de productos
    public MovimientoInventario registrarSalida(
            Long productoId,
            Integer cantidad,
            String observacion) {


        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() ->
                        new RuntimeException("Producto no encontrado"));



        if(producto.getStockActual() < cantidad){

            throw new StockInsuficienteException(
                    "No existe stock suficiente para realizar la salida"
            );

        }



        producto.setStockActual(
                producto.getStockActual() - cantidad
        );


        productoRepository.save(producto);



        MovimientoInventario movimiento =
                new MovimientoInventario();


        movimiento.setProducto(producto);
        movimiento.setTipoMovimiento("SALIDA");
        movimiento.setCantidad(cantidad);
        movimiento.setFecha(LocalDateTime.now());
        movimiento.setObservacion(observacion);



        return movimientoRepository.save(movimiento);

    }





    // Listar movimientos
    public List<MovimientoInventario> listarMovimientos(){

        return movimientoRepository.findAll();

    }





    // Historial de un producto
    public List<MovimientoInventario> historialProducto(Long productoId){

        return movimientoRepository.findByProductoId(productoId);

    }

}