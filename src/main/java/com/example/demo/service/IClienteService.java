package com.example.demo.service;

import java.util.List;
import com.example.demo.models.Cliente;

public interface IClienteService {
    List<Cliente> listClientes();
    Cliente buscaxId(int id);
    void guardar(Cliente cliente);
    void eliminar(int id);
}
