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

import com.example.demo.models.Proveedor;
import com.example.demo.service.IProveedorService;

@Controller
@RequestMapping("/pages/proveedores")
public class ProveedorController {

    @Autowired
    private IProveedorService proveedorService;

    @GetMapping("/")
    public String listaProveedores(Model model) {
        List<Proveedor> listarProveedores = proveedorService.listProveedores();
        model.addAttribute("titulo", "Lista de Proveedores");
        model.addAttribute("proveedores", listarProveedores);
        return "/pages/proveedores/proveedores_lst";
    }

    @GetMapping("/create")
    public String crear(Model model) {
        Proveedor proveedor = new Proveedor();
        model.addAttribute("titulo", "Nuevo Proveedor");
        model.addAttribute("proveedor", proveedor);

        return "/pages/proveedores/proveedores_add";
    }

    @PostMapping("/save")
    public String guardar(@ModelAttribute Proveedor proveedor) {
        proveedorService.guardar(proveedor);
        return "redirect:/pages/proveedores/";
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") int id, Model model) {
        Proveedor proveedor = proveedorService.buscaxId(id);
        model.addAttribute("titulo", "Edita Proveedor");
        model.addAttribute("proveedor", proveedor);

        return "/pages/proveedores/proveedores_add";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int id) {
        proveedorService.eliminar(id);
        return "redirect:/pages/proveedores/";
    }
}
