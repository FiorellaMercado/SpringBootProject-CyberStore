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

import com.example.demo.models.Marca;
import com.example.demo.service.IMarcaService;

@Controller
@RequestMapping("/pages/marcas")
public class MarcaController {

	@Autowired
	private IMarcaService marcaService;
	
	@GetMapping("/")
	public String listaMarcas(Model model) {
		List<Marca> listarMarcas = marcaService.listMarcas();
		model.addAttribute("titulo", "Lista de Marcas");
		model.addAttribute("marcas", listarMarcas);
		return "/pages/marcas/marcas_lst";
	}
	
	@GetMapping("/create")
	public String crear(Model model) {
		Marca marca = new Marca();
		model.addAttribute("titulo", "Nueva Marca");
		model.addAttribute("marca", marca);
		
		return "/pages/marcas/marcas_add";
	}
	
	@PostMapping("/save")
	public String guardar(@ModelAttribute Marca marca) {
		marcaService.guardar(marca);
		return "redirect:/pages/marcas/";
	}
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int id, Model model) {		
		Marca marca = marcaService.buscaxId(id);
		
		model.addAttribute("titulo", "Edita Marca");
		model.addAttribute("marca", marca);
		return "/pages/marcas/marcas_add";
	}
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int id) {		
		 marcaService.eliminar(id);
	     return "redirect:/pages/marcas/";
	}

}
