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

import com.example.demo.models.Categoria;
import com.example.demo.service.ICategoriaService;

@Controller
@RequestMapping("/pages/categorias")
public class CategoriaController {
	
	@Autowired
	private ICategoriaService categoriaService;
	
	@GetMapping("/")
	public String listaCategorias(Model model) {
		List<Categoria> listarCategorias = categoriaService.listCategorias();
		model.addAttribute("titulo", "Lista de Categorias");
		model.addAttribute("categorias", listarCategorias);
		return "/pages/categorias/categorias_lst";
	}
	
	@GetMapping("/create")
	public String crear(Model model) {
		Categoria categoria = new Categoria();
		model.addAttribute("titulo", "Nueva Categoria");
		model.addAttribute("categoria", categoria);
		
		return "/pages/categorias/categorias_add";
	}
	
	@PostMapping("/save")
	public String guardar(@ModelAttribute Categoria categoria) {
		categoriaService.guardar(categoria);
		return "redirect:/pages/categorias/";
	}
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int id, Model model) {		
		Categoria categoria = categoriaService.buscaxId(id);
		
		model.addAttribute("titulo", "Edita Categoria");
		model.addAttribute("categoria", categoria);
		
		return "/pages/categorias/categorias_add";
	}
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int id) {		
		 categoriaService.eliminar(id);
	     return "redirect:/pages/categorias/";
	}
		

}
