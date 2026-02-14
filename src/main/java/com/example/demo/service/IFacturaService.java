package com.example.demo.service;

import java.util.List;
import com.example.demo.models.Factura;

public interface IFacturaService {
    List<Factura> listFacturas();
    Factura buscaxId(int id);
    void guardar(Factura factura);
    void eliminar(int id);
}