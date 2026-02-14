package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.models.DetalleFactura;

@Repository
public interface DetalleFacturaRepository extends CrudRepository<DetalleFactura, Integer> {
}
