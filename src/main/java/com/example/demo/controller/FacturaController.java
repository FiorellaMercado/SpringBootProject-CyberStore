package com.example.demo.controller;

import java.util.ArrayList;
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
import com.example.demo.models.Factura;
import com.example.demo.models.Mercaderia;
import com.example.demo.models.DetalleFactura;
import com.example.demo.service.IClienteService;
import com.example.demo.service.IMercaderiaService;
import com.example.demo.service.IFacturaService;

@Controller
@RequestMapping("/pages/facturas")
public class FacturaController {

    @Autowired
    private IFacturaService facturaService;
    
    @Autowired
    private IClienteService clienteService;

    @Autowired
    private IMercaderiaService mercaderiaService;

    @GetMapping("/")
    public String listaFacturas(Model model) {
        List<Factura> listarFacturas = facturaService.listFacturas();
        model.addAttribute("titulo", "Lista de Facturas");
        model.addAttribute("facturas", listarFacturas);
        return "/pages/facturas/facturas_lst";
    }
    
    @GetMapping("/create")
    public String crear(Model model) {
    	System.out.println("Método crear() alcanzado");
        Factura factura = new Factura();
        
        System.out.println("Clientes: " + clienteService.listClientes());
        System.out.println("Mercaderías: " + mercaderiaService.listMercaderias());
        model.addAttribute("titulo", "Nueva Factura");
        model.addAttribute("factura", factura);
        System.out.println(factura);
        System.out.println("la factura esta o no vacia");
        model.addAttribute("clientes", clienteService.listClientes());	
        model.addAttribute("mercaderia", mercaderiaService.listMercaderias());
        
        return "/pages/facturas/facturas_add";
    }

    @PostMapping("/save")
    public String guardar(@ModelAttribute Factura factura) {

        facturaService.guardar(factura);
        return "redirect:/pages/facturas/";
    }
    
    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") int id, Model model) {
        Factura factura = facturaService.buscaxId(id);
        model.addAttribute("titulo", "Editar Factura");
        model.addAttribute("factura", factura);
        model.addAttribute("clientes", clienteService.listClientes());
        model.addAttribute("mercaderia", mercaderiaService.listMercaderias());
        return "/pages/facturas/facturas_add";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int id) {
        facturaService.eliminar(id);
        return "redirect:/pages/facturas/";
    }
}
