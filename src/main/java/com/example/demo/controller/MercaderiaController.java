package com.example.demo.controller;

import java.io.IOException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.models.Mercaderia;
import com.example.demo.service.IMercaderiaService;
import com.example.demo.service.ICategoriaService;
import com.example.demo.service.IMarcaService;

@Controller
@RequestMapping("/pages/mercaderias")
public class MercaderiaController {

    @Autowired
    private IMercaderiaService mercaderiaService;
    
    @Autowired
    private ICategoriaService categoriaService;
    
    @Autowired
    private IMarcaService marcaService;

    @GetMapping("/")
    public String listaMercaderias(Model model) {
        List<Mercaderia> listarMercaderias = mercaderiaService.listMercaderias();
        model.addAttribute("titulo", "Lista de Mercaderías");
        model.addAttribute("mercaderias", listarMercaderias);
        return "/pages/mercaderias/mercaderias_lst";
    }

    @GetMapping("/create")
    public String crear(Model model) {
        Mercaderia mercaderia = new Mercaderia();
        model.addAttribute("titulo", "Nueva Mercadería");
        model.addAttribute("mercaderia", mercaderia);
        model.addAttribute("categorias", categoriaService.listCategorias());
        model.addAttribute("marcas", marcaService.listMarcas());
        return "/pages/mercaderias/mercaderias_add";
    }

    @PostMapping("/save")
    public String guardar(@ModelAttribute Mercaderia mercaderia, @RequestParam("file") MultipartFile file) {
    	try {
            if (!file.isEmpty()) {
                mercaderia.setImagen(file.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    	mercaderiaService.guardar(mercaderia);
        return "redirect:/pages/mercaderias/";
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") int id, Model model) {
        Mercaderia mercaderia = mercaderiaService.buscaxId(id);
        model.addAttribute("titulo", "Editar Mercadería");
        model.addAttribute("mercaderia", mercaderia);
        model.addAttribute("categorias", categoriaService.listCategorias());
        model.addAttribute("marcas", marcaService.listMarcas());
        return "/pages/mercaderias/mercaderias_add";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int id) {
        mercaderiaService.eliminar(id);
        return "redirect:/pages/mercaderias/";
    }
    
    @GetMapping("/imagen/{id}")
    public ResponseEntity<byte[]> mostrarImagen(@PathVariable("id") int id) {
        Mercaderia mercaderia = mercaderiaService.buscaxId(id);
        if (mercaderia != null && mercaderia.getImagen() != null) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .body(mercaderia.getImagen());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
