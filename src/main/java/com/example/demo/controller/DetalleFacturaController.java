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
import com.example.demo.models.DetalleFactura;
import com.example.demo.models.Factura;
import com.example.demo.models.Mercaderia;
import com.example.demo.service.IDetalleFacturaService;
import com.example.demo.service.IFacturaService;
import com.example.demo.service.IMercaderiaService;

@Controller
@RequestMapping("/pages/detallefacturas")
public class DetalleFacturaController {

    @Autowired
    private IDetalleFacturaService detalleFacturaService;
    
    @Autowired
    private IFacturaService facturaService;
    
    @Autowired
    private IMercaderiaService mercaderiaService;

    @GetMapping("/")
    public String listaDetalleFacturas(Model model) {
        List<DetalleFactura> listarDetalleFacturas = detalleFacturaService.listDetalleFacturas();
        model.addAttribute("titulo", "Lista de Detalle de Facturas");
        model.addAttribute("detallefacturas", listarDetalleFacturas);
        return "/pages/detallefacturas/detallefacturas_lst";
    }

    @GetMapping("/create")
    public String crear(Model model) {
        DetalleFactura detalleFactura = new DetalleFactura();
        List<Factura> facturas = facturaService.listFacturas();
        List<Mercaderia> mercaderias = mercaderiaService.listMercaderias();
        model.addAttribute("titulo", "Nuevo Detalle de Factura");
        model.addAttribute("detalleFactura", detalleFactura);
        model.addAttribute("facturas", facturas);
        model.addAttribute("mercaderias", mercaderias);
        return "/pages/detallefacturas/detallefacturas_add";
    }

    @PostMapping("/save")
    public String guardar(@ModelAttribute DetalleFactura detalleFactura) {
        detalleFacturaService.guardar(detalleFactura);
        return "redirect:/pages/detallefacturas/";
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") int id, Model model) {
        DetalleFactura detalleFactura = detalleFacturaService.buscaxId(id);
        List<Factura> facturas = facturaService.listFacturas();
        List<Mercaderia> mercaderias = mercaderiaService.listMercaderias();
        model.addAttribute("titulo", "Editar Detalle de Factura");
        model.addAttribute("detalleFactura", detalleFactura);
        model.addAttribute("facturas", facturas);
        model.addAttribute("mercaderias", mercaderias);
        return "/pages/detallefacturas/detallefacturas_add";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int id) {
        detalleFacturaService.eliminar(id);
        return "redirect:/pages/detallefacturas/";
    }
}
