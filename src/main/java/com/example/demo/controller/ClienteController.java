package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Cliente;
import com.example.demo.service.IClienteService;

@Controller
@RequestMapping("/pages/clientes")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping("/")
    public String listaClientes(Model model) {
        List<Cliente> listarClientes = clienteService.listClientes();
        model.addAttribute("titulo", "Lista de Clientes");
        model.addAttribute("clientes", listarClientes);
        return "/pages/clientes/clientes_lst";
    }

    @GetMapping("/create")
    public String crear(Model model) {
        Cliente cliente = new Cliente();
        model.addAttribute("titulo", "Nuevo Cliente");
        model.addAttribute("cliente", cliente);
        return "/pages/clientes/clientes_add";
    }

    @PostMapping("/save")
    public String guardar(@ModelAttribute Cliente cliente) {
        clienteService.guardar(cliente);
        return "redirect:/pages/clientes/";
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") int id, Model model) {
        Cliente cliente = clienteService.buscaxId(id);
        model.addAttribute("titulo", "Editar Cliente");
        model.addAttribute("cliente", cliente);
        return "/pages/clientes/clientes_add";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int id) {
        clienteService.eliminar(id);
        return "redirect:/pages/clientes/";
    }
}
