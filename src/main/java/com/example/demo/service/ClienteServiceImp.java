package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.models.Cliente;
import com.example.demo.repository.ClienteRepository;

@Service
public class ClienteServiceImp implements IClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> listClientes() {
        return (List<Cliente>) clienteRepository.findAll();
    }

    @Override
    public Cliente buscaxId(int id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public void guardar(Cliente cliente) {
        clienteRepository.save(cliente);
    }
    
    

    @Override
    public void eliminar(int id) {
        clienteRepository.deleteById(id);
    }
}
