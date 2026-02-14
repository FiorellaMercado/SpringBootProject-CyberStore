package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Categoria;
import com.example.demo.repository.CategoriaRepository;

@Service
public class CategoriaServiceImp implements ICategoriaService{
  
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Override
	public List<Categoria> listCategorias() {
		return  (List<Categoria>) categoriaRepository.findAll();
	}

	@Override
	public void guardar(Categoria categoria) {
		categoriaRepository.save(categoria);			
	}

	@Override
	public Categoria buscaxId(int id) {
		return categoriaRepository.findById(id).orElse(null);
	}

	@Override
	public void eliminar(int id) {
		categoriaRepository.deleteById(id);
		
	}

}
