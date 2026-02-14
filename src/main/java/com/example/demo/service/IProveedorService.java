package com.example.demo.service;

import java.util.List;
import com.example.demo.models.Proveedor;

public interface IProveedorService {
    public List<Proveedor> listProveedores();
    public void guardar(Proveedor proveedor);
    public Proveedor buscaxId(int id);
    public void eliminar(int id);
}
