package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Proveedor;
import com.example.demo.repository.ProveedorRepository;

@Service
public class ProveedorServiceImp implements IProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public List<Proveedor> listProveedores() {
        return (List<Proveedor>) proveedorRepository.findAll();
    }

    @Override
    public void guardar(Proveedor proveedor) {
        proveedorRepository.save(proveedor);
    }

    @Override
    public Proveedor buscaxId(int id) {
        return proveedorRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(int id) {
        proveedorRepository.deleteById(id);
    }
}
