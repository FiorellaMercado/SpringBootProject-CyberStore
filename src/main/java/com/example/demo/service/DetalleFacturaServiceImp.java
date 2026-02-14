package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.models.DetalleFactura;
import com.example.demo.repository.DetalleFacturaRepository;

@Service
public class DetalleFacturaServiceImp implements IDetalleFacturaService {

    @Autowired
    private DetalleFacturaRepository detalleFacturaRepository;

    @Override
    public List<DetalleFactura> listDetalleFacturas() {
        return (List<DetalleFactura>) detalleFacturaRepository.findAll();
    }

    @Override
    public DetalleFactura buscaxId(int id) {
        return detalleFacturaRepository.findById(id).orElse(null);
    }

    @Override
    public void guardar(DetalleFactura detalleFactura) {
        detalleFacturaRepository.save(detalleFactura);
    }

    @Override
    public void eliminar(int id) {
        detalleFacturaRepository.deleteById(id);
    }
}
