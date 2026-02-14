package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.models.Factura;
import com.example.demo.repository.FacturaRepository;

@Service
public class FacturaServiceImp implements IFacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Override
    public List<Factura> listFacturas() {
        return (List<Factura>) facturaRepository.findAll();
    }

    @Override
    public Factura buscaxId(int id) {
        return facturaRepository.findById(id).orElse(null);
    }

    @Override
    public void guardar(Factura factura) {
        facturaRepository.save(factura);
    }

    @Override
    public void eliminar(int id) {
        facturaRepository.deleteById(id);
    }
}
