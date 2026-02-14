package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.models.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer> {
}
