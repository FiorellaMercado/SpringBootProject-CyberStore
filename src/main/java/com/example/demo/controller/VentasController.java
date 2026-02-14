package com.example.demo.controller;

import com.example.demo.models.Cliente;
import com.example.demo.models.Factura;
import com.example.demo.models.Mercaderia;
import com.example.demo.models.DetalleFactura;
import com.example.demo.service.IFacturaService;
import com.example.demo.service.IDetalleFacturaService;
import com.example.demo.service.IMercaderiaService;
import com.example.demo.service.IClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/movimientos")
public class VentasController {

    @Autowired
    private IFacturaService facturaService;

    @Autowired
    private IDetalleFacturaService detalleFacturaService;

    @Autowired
    private IMercaderiaService mercaderiaService;

    @Autowired
    private IClienteService clienteService;

   
    @GetMapping("/create")
    public String crearVenta(Model model) {
        Factura factura = new Factura();

        
        factura.getDetalleFacturas().add(new DetalleFactura());

        List<Cliente> clientes = clienteService.listClientes();
        List<Mercaderia> mercaderias = mercaderiaService.listMercaderias();

        
        model.addAttribute("titulo", "Nueva Venta");
        model.addAttribute("factura", factura);
        model.addAttribute("clientes", clientes);
        model.addAttribute("mercaderias", mercaderias);

        return "/movimientos/ventas_add";  
    }

    

    
    
    @PostMapping("/save")
    public String guardarVenta(@ModelAttribute Factura factura) {
    	List<DetalleFactura> detalles = factura.getDetalleFacturas();
    	
    	List<DetalleFactura> detallesValidos = detalles.stream()
    		    .filter(detalle -> detalle.getCantidad() > 0 && 
    		                       detalle.getMercaderia() != null && 
    		                       detalle.getMercaderia().getId() != 0) 
    		    .collect(Collectors.toList());


            
            if (detallesValidos.isEmpty()) {
               
                return "redirect:/movimientos/create?error=No+hay+detalles+válidos";
            }
        
        factura.setTotal(factura.getTotal());

       
        facturaService.guardar(factura);

       
        for (DetalleFactura detalle : detallesValidos) {
            detalle.setFactura(factura);  
            detalleFacturaService.guardar(detalle);  
        }

        return "redirect:/movimientos/";  
    }

    
    @GetMapping("/")
    public String listarVentas(Model model) {
        List<Factura> facturas = facturaService.listFacturas();
        model.addAttribute("titulo", "Lista de Ventas");
        model.addAttribute("facturas", facturas);

        return "/movimientos/ventas_lst";  
    }
    
    @GetMapping("/edit/{id}")
    public String editarVenta(@PathVariable("id") int id, Model model) {
        Factura factura = facturaService.buscaxId(id);

        
        if (factura == null) {
            return "redirect:/movimientos/?error=Factura+no+encontrada";
        }

        
        model.addAttribute("titulo", "Editar Venta");
        model.addAttribute("factura", factura);
        model.addAttribute("clientes", clienteService.listClientes());
        model.addAttribute("mercaderias", mercaderiaService.listMercaderias());

        return "/movimientos/ventas_add";  
    }

    
    @GetMapping("/delete/{id}")
    public String eliminarVenta(@PathVariable("id") int id) {
        
        Factura factura = facturaService.buscaxId(id);
        if (factura != null) {
            
            for (DetalleFactura detalle : factura.getDetalleFacturas()) {
                detalleFacturaService.eliminar(detalle.getId());
            }
         
            facturaService.eliminar(id);
        }

        return "redirect:/movimientos/";  
    }
    
    @GetMapping("/mercaderias/precio/{id}")
    @ResponseBody
    public BigDecimal obtenerPrecio(@PathVariable int id) {
        Mercaderia mercaderia = mercaderiaService.buscaxId(id);
        if (mercaderia != null) {
            System.out.println("Precio encontrado: " + mercaderia.getPrecio_vta());
            return BigDecimal.valueOf(mercaderia.getPrecio_vta());
        }
        System.out.println("Mercadería no encontrada");
        return BigDecimal.ZERO;
    }
}

