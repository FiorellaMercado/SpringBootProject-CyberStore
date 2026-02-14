package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.models.Proveedor;

public interface ProveedorRepository extends CrudRepository<Proveedor, Integer> {
}
