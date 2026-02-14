package com.example.demo.service;

import java.util.List;
import com.example.demo.models.DetalleFactura;

public interface IDetalleFacturaService {
    List<DetalleFactura> listDetalleFacturas();
    DetalleFactura buscaxId(int id);
    void guardar(DetalleFactura detalleFactura);
    void eliminar(int id);
}
